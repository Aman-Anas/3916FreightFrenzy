package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

// Square demonstration autopath

@Autonomous(name = "LittleCircle", group = "Apex Robotics 3916")
public class LittleSquare extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        FtcDashboard dashboard = FtcDashboard.getInstance();

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        FTCLibRobotFunctions bot = new FTCLibRobotFunctions();
        bot.initBot(hardwareMap);

        bot.slideMotor.encoder.reset();

        TrajectorySequence forwardSeg = drive.trajectorySequenceBuilder(new Pose2d(0, 0, Math.toRadians(0)))
                .lineToLinearHeading(new Pose2d(50, 0, Math.toRadians(0)))
                .waitSeconds(1.0)
                .build();

        TrajectorySequence leftSeg = drive.trajectorySequenceBuilder(new Pose2d(50, 0, Math.toRadians(0)))
                .lineToLinearHeading(new Pose2d(50, 50, Math.toRadians(0)))
                .waitSeconds(1.0)
                .build();

        TrajectorySequence backSeg = drive.trajectorySequenceBuilder(new Pose2d(50, 50, Math.toRadians(0)))
                .lineToLinearHeading(new Pose2d(0, 50, Math.toRadians(0)))
                .waitSeconds(1.0)
                .build();

        TrajectorySequence rightSeg = drive.trajectorySequenceBuilder(new Pose2d(0, 50, Math.toRadians(0)))
                .lineToLinearHeading(new Pose2d(0, 0, Math.toRadians(0)))
                .waitSeconds(1.0)
                .build();

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            drive.followTrajectorySequence(forwardSeg);
            drive.followTrajectorySequence(leftSeg);
            drive.followTrajectorySequence(backSeg);
            drive.followTrajectorySequence(rightSeg);
        }
    }
}
