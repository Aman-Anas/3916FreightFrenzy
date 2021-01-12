package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Autonomous(name="Auto Testing", group="Apex Robotics 3916")
public class Autonomous_Testing extends LinearOpMode {
    //Add autonomous stuff here.
    //https://www.learnroadrunner.com/
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;

        Trajectory traj = drive.trajectoryBuilder(new Pose2d(-62.0, 50, 0), 0)
                .splineToSplineHeading(new Pose2d(-62.01, 50), Math.toRadians(10))
                .splineToSplineHeading(new Pose2d(50, 60), 0)
                .splineToSplineHeading(new Pose2d(10, 60), 0)
                .build();

        drive.followTrajectory(traj);

        sleep(2000);
    }
}