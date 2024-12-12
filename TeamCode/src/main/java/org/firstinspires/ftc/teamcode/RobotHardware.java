package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class RobotHardware {

    /* Declare OpMode members. */
    private LinearOpMode myOpMode = null;

    public ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;

    private DcMotor slideMotorL = null;
    private DcMotor slideMotorR = null;

    private Servo horizontal1 = null;
    private Servo horizontal2 = null;
    private Servo lextend = null;
    private Servo rextend = null;
    private Servo leftOutTake = null;
    private Servo rightOutTake = null;
    private Servo inClaw = null;
    private Servo outClaw = null;

    public int leftFrontTarget;
    public int leftBackTarget;
    public int rightFrontTarget;
    public int rightBackTarget;

    public double COUNTS_PER_MOTOR_REV = 537.7;
    public double WHEEL_DIAMETER_INCHES = 3.77953;
    public double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV) / (WHEEL_DIAMETER_INCHES * Math.PI);

    // public static final double MID_SERVO = 0.8 ;
    public static final double SERVO_UP = 0.90;
    public static final double SERVO_DOWN = -0.90;
    public static final double SLIDE_UP_POWER = 1.0;
    public static final double SLIDE_DOWN_POWER = -0.50;

    public double SLIDE_TICKS_PER_DEGREE = 28.0 * 19.2 / 360.0;

    public double SLIDE_START = 0.0 * SLIDE_TICKS_PER_DEGREE;
    public double SLIDE_HIGH_RUNG = -1000 * SLIDE_TICKS_PER_DEGREE;
    public double SLIDE_LOW_BASKET = 360.0 * SLIDE_TICKS_PER_DEGREE;
    public double SLIDE_HIGH_BASKET = - 2100 * SLIDE_TICKS_PER_DEGREE;

    public double slidePosition = (int)SLIDE_START;


    public RobotHardware(LinearOpMode OpMode) {myOpMode = OpMode;}

    /**
     * Initialize all the robot's hardware.
     * This method must be called ONCE when the OpMode is initialized.
     * <p>
     * All of the hardware devices are accessed via the hardware map, and initialized.
     */

    public void init() {
        // Define and Initialize Motors (note: need to use reference to actual OpMode).
        leftFrontDrive = myOpMode.hardwareMap.get(DcMotor.class, "left_front_drive");
        leftBackDrive = myOpMode.hardwareMap.get(DcMotor.class, "left_back_drive");
        rightFrontDrive = myOpMode.hardwareMap.get(DcMotor.class, "right_front_drive");
        rightBackDrive = myOpMode.hardwareMap.get(DcMotor.class, "right_back_drive");
        slideMotorL = myOpMode.hardwareMap.get(DcMotor.class, "slideMotorL");
        slideMotorR = myOpMode.hardwareMap.get(DcMotor.class, "slideMotorR");



        // To drive forward, most robots need the motor on one side to be reversed, because the axles point on opposite directions.
        // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels. Gear Reduction or 90 Deg drives mat require direction flips.
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
        slideMotorL.setDirection(DcMotor.Direction.FORWARD);
        slideMotorR.setDirection(DcMotor.Direction.REVERSE);

        leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideMotorL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideMotorR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        slideMotorL.setTargetPosition(0);
        slideMotorR.setTargetPosition(0);
//        slideMotorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        slideMotorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideMotorL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideMotorR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        //Define and initialize ALL installed servos.
        horizontal1 = myOpMode.hardwareMap.get(Servo.class, "horizontal1");
        lextend = myOpMode.hardwareMap.get(Servo.class, "lextend");
        rextend = myOpMode.hardwareMap.get(Servo.class, "rextend");
        leftOutTake = myOpMode.hardwareMap.get(Servo.class, "leftOutTake");
        rightOutTake = myOpMode.hardwareMap.get(Servo.class, "rightOutTake");

        inClaw = myOpMode.hardwareMap.get(Servo.class, "inClaw");
        outClaw = myOpMode.hardwareMap.get(Servo.class, "outClaw");


        myOpMode.telemetry.addData(">", "Hardware Initialized");
        myOpMode.telemetry.update();
    }

    /**
     * Calculates the left/right motor powers required to achieve the requested to achieve the requested
     * robot motions: Drive (Axial motion) and Turn (Yaw motion).
     * Then sends these power levels to the motors.
     *
     * @param Drive     Fwd/Rev driving power(-1.0 to 1.0) +ve is forward
     * @param Turn      Right/Left turning power(-1.0 to 1.0) +ve is CW
     * @param Strafe
     */
    public void driveRobotCentric(double Drive,double Strafe, double Turn){
        //Combine drive and turn for blended motion.
        double leftFrontPower = Drive + Strafe + Turn;
        double leftBackPower = Drive - Strafe + Turn;
        double rightFrontPower = Drive - Strafe - Turn;
        double rightBackPower = Drive + Strafe -  Turn;

        //Scale the values so neither exceed +/-1.0
        double max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0)
        {
            leftFrontPower /= max;
            leftBackPower /= max;
            rightFrontPower /= max;
            rightBackPower /= max;
        }

        //Use existing function to drive both wheels.
        setDrivePower(leftFrontPower, leftBackPower, rightFrontPower, rightBackPower);
    }

    public void driveEncoder(double speed, double leftFrontInches, double leftBackInches, double rightFrontInches, double rightBackInches, double timeOutSecs){
        // drives only while myOpMode is active
        if(myOpMode.opModeIsActive()){
            setSlidePosition();


            //determine new target position
            leftFrontTarget = leftFrontDrive.getCurrentPosition() + (int)(leftFrontInches * COUNTS_PER_INCH);
            leftBackTarget = leftBackDrive.getCurrentPosition() + (int)(leftBackInches * COUNTS_PER_INCH);
            rightFrontTarget = rightFrontDrive.getCurrentPosition() + (int)(rightFrontInches * COUNTS_PER_INCH);
            rightBackTarget = rightBackDrive.getCurrentPosition() + (int)(rightBackInches * COUNTS_PER_INCH);

            leftFrontDrive.setTargetPosition(leftFrontTarget - 1);
            leftBackDrive.setTargetPosition(leftBackTarget - 1 );
            rightFrontDrive.setTargetPosition(rightFrontTarget - 1);
            rightBackDrive.setTargetPosition(rightBackTarget - 1);

            //turn on RUN_TO_POSITION
            leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the time and start motion

            runtime.reset();
            setDrivePower(Math.abs(speed), Math.abs(speed), Math.abs(speed), Math.abs(speed));

            while ((myOpMode.opModeIsActive() && (runtime.seconds() < timeOutSecs) &&
                    (leftFrontDrive.isBusy() && leftBackDrive.isBusy() &&
                            rightFrontDrive.isBusy() && rightBackDrive.isBusy()))){

                //display it for driver

                myOpMode.telemetry.addData("Running to ", " %7d :%7d :%7d :%7d",
                        leftFrontTarget, leftBackTarget, rightFrontTarget, rightBackTarget);
                myOpMode.telemetry.addData("Currently at ", "%7d ;%7d :%7d :%7d",
                        leftFrontDrive.getCurrentPosition(), leftBackDrive.getCurrentPosition(),
                        rightFrontDrive.getCurrentPosition(), rightBackDrive.getCurrentPosition());
                myOpMode.telemetry.update();
            }

            setDrivePower(0, 0, 0, 0 );
            leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            leftBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            myOpMode.sleep(200); //optional pause after each move
        }
    }

    public void setSlidePosition(){
        slideMotorL.setTargetPosition((int)(slidePosition));
        slideMotorR.setTargetPosition((int)(slidePosition));

        ((DcMotorEx) slideMotorL).setVelocity(2500);
        ((DcMotorEx) slideMotorR).setVelocity(2500);
        slideMotorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideMotorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    /**
     * Pass the requested wheel motor powers to the appropriate hardware drive motors.
     *
     * @param leftFrontWheel     Fwd/Rev driving power (-1.0 to 1.0) +ve is forward
     * @param leftBackWheel     Fwd/Rev driving power (-1.0 to 1.0) +ve is forward
     * @param rightFrontWheel    Fwd/Rev driving power (-1.0 to 1.0) +ve is forward
     * @param rightBackWheel    Fwd/Rev driving power (-1.0 to 1.0) +ve is forward
     */
    public void setDrivePower(double leftFrontWheel, double leftBackWheel, double rightFrontWheel, double rightBackWheel) {
        //Output the values to the motor drives.
        leftFrontDrive.setPower(leftFrontWheel);
        leftBackDrive.setPower(leftBackWheel);
        rightFrontDrive.setPower(rightFrontWheel);
        rightBackDrive.setPower(rightBackWheel);
    }

    /**
     *  Pass the requested slide power to the appropriate hardware drive motor
     *
     * @param power driving power (-1.0 to 1.0)
     */
    public void setSlidePower(double power) {
        slideMotorL.setPower(power);
        slideMotorR.setPower(-power);
    }

    /**
     * Send the two hand-servos to opposing (mirrored) positions, based on the passed offset.
     * the extends are what extend the slides
     * @param offset
     */
    public void setIntakePosition(double offset){
        //whatever value you subtract from lextend should be added to rextend and vise versa
        if (offset == 1) {
            //retracted
            lextend.setPosition(1);
            rextend.setPosition(0.015);
        } else if (offset == 0) {
            //extended
            lextend.setPosition(0.8);
            rextend.setPosition(0.215);
        }
    }

    /**
     * Send the two hand-servos to opposing (mirrored) positions, based on the passed offset.
     * the horizontals are what rotates the intake
     * @param offset
     */
    public void setHorizontalPosition(double offset) {
        if (offset == 1) {
            horizontal1.setPosition(0.22);
            lextend.setPosition(.95);
            rextend.setPosition(0.065);
            //0.9
        } else if (offset == 0) {
            horizontal1.setPosition(0.8);
        }
    }

    //changes here affect both duo and solo , add to left and right.
    public void setVerticalPower(double power) {
        if (power == 1) {
            leftOutTake.setPosition(0.1);
            rightOutTake.setPosition(0.9);
        }else if (power == 0){
            leftOutTake.setPosition(.65);
            rightOutTake.setPosition(.35);
        }

    }

    public void setInClawPosition(double power){
        if(power == 1){
            inClaw.setPosition(0.3);
        }else if(power == 0){
            inClaw.setPosition(0);
        }
    }

    public void setOutClawPosition(double power){
        if(power == 1){
            outClaw.setPosition(0.20);
        }else if(power == 0){
            outClaw.setPosition(0.13);
        }
    }
}