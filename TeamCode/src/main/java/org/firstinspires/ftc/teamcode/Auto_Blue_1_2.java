package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Autonomous(name="Auto Blue 1 2", group="Apex Robotics 3916")
public class Auto_Blue_1_2 extends LinearOpMode {
    //https://www.learnroadrunner.com/
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;

        Trajectory traj = drive.trajectoryBuilder(new Pose2d(-62.0, 50, 0), 0)
                .splineToSplineHeading(new Pose2d(-61.99, 50), Math.toRadians(20))
                .splineToSplineHeading(new Pose2d(30, 37), 0)
                .splineToLinearHeading(new Pose2d(8, 37), 0)
                .build();

        drive.followTrajectory(traj);

        sleep(2000);
    }
}