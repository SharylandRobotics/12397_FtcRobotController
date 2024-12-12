package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.RobotHardware;


@Autonomous(name = "Robot: Auto By time BPPP", group = "Robot")

public class AutoByTimeBPPP extends LinearOpMode{

    RobotHardware robot = new RobotHardware(this);
    public ElapsedTime runtime = new ElapsedTime();

    static final double FORWARD_SPEED = 0.5;
    static final double STRAFE_SPEED = 0.5;
    static final double TURN_SPEED = 0.4;

    double drive = 0;
    double strafe  = 0;
    double turn = 0;

    @Override
    public void runOpMode(){

        robot.init();
        waitForStart();


//    the way auto by time works is by doing different inputs at a certain voltage for a certain
//    point in time, you pretty much declare how much voltage you wish to supply which operation
//    and then use the sleep command to establish the amount of milliseconds to do it for.
//    after an action and its time are done, make sure to supply that action with a voltage of 0

        //drive to net zone
        robot.driveRobotCentric(-0.5, 0, 0);
        robot.setHorizontalPosition(0.5);
        robot.setIntakePosition(1);
        sleep(1350);

        robot.driveRobotCentric(0,0,0);
        sleep(100);
        //aim to basket
        robot.driveRobotCentric(0, -0.4, -0.5);
        sleep(200);

        robot.driveRobotCentric(0, 0, 0);
        robot.setSlidePower(0.6);
        sleep(700);
        robot.setVerticalPower(-1);
        sleep(2000);
        robot.setSlidePower(-0.9);
        robot.setVerticalPower(1);
        sleep(850);
        robot.setSlidePower(0);

        robot.driveRobotCentric(0.65, 0.5, -0.9);
        sleep(300);
        robot.driveRobotCentric(0, 0.65, -0.9);
        sleep(100);
        robot.driveRobotCentric(0.65, 0, 0);
        sleep(1100);
        robot.driveRobotCentric(0.0,-0.65, 0);
        sleep(500);
        robot.driveRobotCentric(-0.65, 0, 0);
        sleep(1200);
        robot.driveRobotCentric(0, 0, 0);
        sleep(300);
        robot.driveRobotCentric(0.65, 0.65, 0);
        sleep(100);
        robot.driveRobotCentric(0.65, 0, 0);
        sleep(1100);
        robot.driveRobotCentric(0.0,-0.75, 0);
        sleep(300);
        robot.driveRobotCentric(-0.65, 0, 0);
        sleep(1200);
        robot.driveRobotCentric(0, 0, 0);
        sleep(100);
        robot.driveRobotCentric(0.4, 0,0);
        sleep(200);
        robot.driveRobotCentric(0, 0, 0);
        sleep(100);
        robot.driveRobotCentric(0, 0.5, -0.3);
        sleep(200);
        robot.driveRobotCentric(0, 0, 0);
        sleep(200);
        robot.driveRobotCentric(0.65, 0, 0);
        sleep(1000);
        robot.driveRobotCentric(0,0,0);
        sleep(100);
        robot.driveRobotCentric(0, 0.75, 0);
        sleep(1200);
        robot.driveRobotCentric(0,0,0);
        sleep(200);
        robot.driveRobotCentric(0,-0.6,0);
        sleep(1500);
        robot.driveRobotCentric(-0.5, 0, 0);
        sleep(2000);
        robot.driveRobotCentric(0.75, 0,0);
        sleep(1200);
        robot.driveRobotCentric(0, 0.75,0);
        sleep(1350);
    }

}
