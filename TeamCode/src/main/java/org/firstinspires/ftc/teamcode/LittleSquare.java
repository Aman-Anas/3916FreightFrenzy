package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequenceBuilder;

// Square demonstration autopath

@Autonomous(name = "LittleSquare", group = "Apex Robotics 3916")
public class LittleSquare extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        //FtcDashboard dashboard = FtcDashboard.getInstance();

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        //FTCLibRobotFunctions bot = new FTCLibRobotFunctions();
        //bot.initBot(hardwareMap);

        //bot.slideMotor.encoder.reset();

        TrajectorySequence forwardSeg = drive.trajectorySequenceBuilder(new Pose2d(0, 0))
                .splineToLinearHeading(new Pose2d(30, 0, 0), 0)
                .waitSeconds(.5)
                .splineToLinearHeading(new Pose2d(30, 30, 0), 0)
                .waitSeconds(.5)
                .splineToLinearHeading(new Pose2d(0, 30, 0), 0)
                .waitSeconds(.5)
                .splineToLinearHeading(new Pose2d(0, 0, 0), 0)
                .waitSeconds(.5)
                .build();

        /*Trajectory leftSeg = drive.trajectoryBuilder(forwardSeg.end())
                .splineTo(new Vector2d(20, 20), 0)
                .build();

        Trajectory backSeg = drive.trajectoryBuilder(leftSeg.end())
                .splineTo(new Vector2d(0, 20), 0)
                .build();

        Trajectory rightSeg = drive.trajectoryBuilder(backSeg.end())
                .splineTo(new Vector2d(0, 0), 0)
                .build();*/

        waitForStart();

        drive.followTrajectorySequence(forwardSeg);

        /*while (opModeIsActive() && !isStopRequested()) {
            drive.followTrajectorySequence(forwardSeg);
            //drive.followTrajectory(leftSeg);
            //drive.followTrajectory(backSeg);
            //drive.followTrajectory(rightSeg);
        }*/
    }
}
