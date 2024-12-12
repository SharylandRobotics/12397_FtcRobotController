package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.RobotHardware;


@TeleOp(name="Robot Centric (duo) NEW", group="Robot")

public class RobotCentricDuo extends LinearOpMode {

    // Create a RobotHardware object to be used to access robot hardware.
    // Prefix any hardware functions with "robot." to access this class.
    RobotHardware robot = new RobotHardware(this);

    @Override
    public void runOpMode() {
        double drive = 0;
        double strafe = 0;
        double turn = 0;
        double slide = 0;
        double hslide = 0;
        double vertical = 0.5;
        double horizontalOffset = 0;
        double extendOffset = 1;

        // initialize all the hardware, using the hardware class. See how clean and simple this is?
        robot.init();

        // Send telemetry message to signify robot waiting;
        // Wait for the game to start (driver presses START)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Run wheels in POV mode (note: The joystick goes negative when pushed forward, so negate it)
            // In this mode the Left stick moves the robot fwd and back, the Right stick turns left and right.
            // This way it's also easy to just drive straight, or just turn.
            drive = -gamepad2.left_stick_y;
            strafe = gamepad2.left_stick_x;
            turn  =  gamepad2.right_stick_x;

            // Combine drive and turn for blended motion. Use RobotHardware class
            robot.driveRobotCentric(drive, strafe, turn);

            // Use gamepad left & right Bumpers to open and close the claw
            // Use the SERVO constants defined in RobotHardware class.
            // Each time around the loop, the servos will move by a small amount.
            // Limit the total offset to half of the full travel range


            // Move both servos to new position.  Use RobotHardware class

            // Use gamepad buttons to move arm up (Y) and down (A)
            // Use the MOTOR constants defined in RobotHardware class.
            if (gamepad2.y) {
                vertical = RobotHardware.SERVO_UP;
            } else if (gamepad2.a) {
                vertical = RobotHardware.SERVO_DOWN;
            } else {
                vertical = 0.5;
            }

            robot.setVerticalPower(vertical);


            //moves vertical slides
            if(gamepad2.dpad_up){
                slide = robot.SLIDE_UP_POWER;
                horizontalOffset = 0.00;
            }else if (gamepad2.dpad_down){
                slide = robot.SLIDE_DOWN_POWER;
            }else{
                slide = 0;
            }

            robot.setSlidePower(slide);

            if (gamepad2.x) {
                horizontalOffset = 1;
            }
            else if (gamepad2.b) {
                horizontalOffset = 0.00;
            }

            robot.setHorizontalPosition(horizontalOffset);

            if (gamepad2.dpad_left) {
                extendOffset = 1;

            } else if (gamepad2.dpad_right) {
                extendOffset = 0;

            }

            robot.setIntakePosition(extendOffset);

            // Send telemetry messages to explain controls and show robot status
            telemetry.addData("Drive", "Left Stick");
            telemetry.addData("Turn", "Right Stick");
            telemetry.addData("Slide Up/Down", "Dpad_Up & Dpad_Down");
            telemetry.addData("HSlide Up/Down", "Dpad_Right & Dpad_Left");
            telemetry.addData("OutTake", "Y & A Buttons");
            telemetry.addData("Horizontal", "B & X Buttons");
            telemetry.addData("Intake", "left & right bumper");
            telemetry.addData("-", "-------");

            telemetry.addData("Drive Power", "%.2f", drive);
            telemetry.addData("Turn Power",  "%.2f", turn);
            telemetry.addData("Slide Power",  "%.2f", slide);
            telemetry.addData("HSlidePower", "%.2f", hslide);
            telemetry.addData("Outtake Power", "%.2f", vertical);
            telemetry.addData("Horizontal Position", "%.2f", horizontalOffset);
            telemetry.addData("Hand Position",  "Offset = %.2f", extendOffset);
            telemetry.update();

            // Pace this loop so hands move at a reasonable speed.
            sleep(50);
        }
    }
}