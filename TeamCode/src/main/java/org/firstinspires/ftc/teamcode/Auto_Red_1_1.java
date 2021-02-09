package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Autonomous(name="Auto Red 1 1", group="Apex Robotics 3916")
public class Auto_Red_1_1 extends LinearOpMode {
    //https://www.learnroadrunner.com/
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        //wait for start so the robot doesn't just vibe before the driver hits start
        waitForStart();

        if (isStopRequested()) return;
        Pose2d startPose = new Pose2d(-62.0, -50, 0);
        drive.setPoseEstimate(startPose);
        Trajectory traj = drive.trajectoryBuilder(new Pose2d(-62.0, -50, 0), 0)
                .splineToSplineHeading(new Pose2d(-61.99, -50, 0.0), Math.toRadians(-10.0))
                .splineToSplineHeading(new Pose2d(50.0, -60.0), 0.0)
                .splineToSplineHeading(new Pose2d(10.0, -60.0), 0.0)
                .build();

        drive.followTrajectory(traj);

        sleep(2000);
    }
}