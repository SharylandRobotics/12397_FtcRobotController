package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.RobotHardware;

// lemoine was here

@Autonomous(name = "autoTest", group = "Robot")

public class AutoTest extends LinearOpMode{

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
        robot.driveRobotCentric(0, 0, 0);
        robot.setSlidePower(1);
        sleep(900);
        robot.setSlidePower(-0.8);
        sleep(825);

    }

}
