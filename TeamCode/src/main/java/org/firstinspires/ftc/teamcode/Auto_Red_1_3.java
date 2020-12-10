package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Autonomous(name="Auto Red 1 3", group="Apex Robotics 3916")
public class Auto_Red_1_3 extends LinearOpMode {
    //https://www.learnroadrunner.com/
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;

        Trajectory traj = drive.trajectoryBuilder(new Pose2d(-62.0, -50, 0), 0)
                .splineToSplineHeading(new Pose2d(-62.01, -50.01), Math.toRadians(-10))
                .splineToSplineHeading(new Pose2d(-5, -60), 0)
                .splineToConstantHeading(new Vector2d(-20, -50), Math.toRadians(10))
                .splineToLinearHeading(new Pose2d(10, -20), Math.toRadians(-20))
                .build();

        drive.followTrajectory(traj);

        sleep(2000);
    }
}