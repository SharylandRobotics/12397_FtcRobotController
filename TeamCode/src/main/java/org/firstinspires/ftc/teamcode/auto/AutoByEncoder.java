package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.RobotHardware;

@Autonomous(name =  "Auto By Encoder 100pts", group = "Robot")

public class AutoByEncoder extends LinearOpMode{
    RobotHardware robot = new RobotHardware(this);
    ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode(){
        robot.init();

        waitForStart();
        runtime.reset();


        //strafing is always 2 inches less than the inches stated in code

        //driving is always 1 inch more than said in code.

        //keeps slides retracted until they are used
        robot.setIntakePosition(1);

        //forward
        robot.driveEncoder(0.75, 29, 29, 29, 29, 5);
        //back
        robot.driveEncoder(0.75,-3.5,-3.5,-3.5,-3.5, 5);
        //strafe right
        robot.driveEncoder(0.75,27,-27,-27,27,5);
        //forward
        robot.driveEncoder(0.75, 24, 24, 24, 24,5);
        //strafe right
        robot.driveEncoder(0.75, 8, -8, -8, 8, 5);
        //drive backward and drop of specimen in oz
        robot.driveEncoder(0.75, -37, -37, -37, -37, 5);
        //forward
        robot.driveEncoder(0.75, 34, 34, 34, 34, 5);
        //right
        robot.driveEncoder(0.75, 11, -11, -11, 11, 5);
        //backward drop off specimen 2 in oz
        robot.driveEncoder(0.75, -34, -34, -34, -34, 5);
        //forward
        robot.driveEncoder(0.75, 34, 34, 34, 34, 5);
        //right
        robot.driveEncoder(0.6, 10, -10, -10, 10, 5);
        //backward drop off specimen 3 in oz
        robot.driveEncoder(0.75, -35, -35, -35, -35, 5);


    }
}