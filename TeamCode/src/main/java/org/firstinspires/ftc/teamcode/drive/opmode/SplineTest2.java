package org.firstinspires.ftc.teamcode.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequenceBuilder;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

/*
 * This is an example of a more complex path to really test the tuning.
 */
@Autonomous(group = "drive")
public class SplineTest2 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;

        TrajectorySequence traj = drive.trajectorySequenceBuilder(new Pose2d(0,0))
                .lineToLinearHeading(new Pose2d(-55.0, 53.0, 1.5707963267948966))
                .splineToLinearHeading(new Pose2d(-11.0, 43.0, 1.5707963267948966), 0.0)
                .splineToLinearHeading(new Pose2d(8.0, 45.0, 0.0), 0.0)
                .splineToLinearHeading(new Pose2d(38.0, 45.0, 0.0), 0.0)
                .lineToLinearHeading(new Pose2d(41.0, 49.0, Math.toRadians(30)))
                .splineToLinearHeading(new Pose2d(38.0, 45.0, 0.0), 0.0)
                .lineToLinearHeading(new Pose2d(8.0, 45.0, 0.0))
                .lineToLinearHeading(new Pose2d(3, 37.0, Math.toRadians(45)))
                .splineToLinearHeading(new Pose2d(8.0, 45.0, 0.0), 0.0)
                .splineToLinearHeading(new Pose2d(38.0, 45.0, 0.0), 0.0)
                .lineToLinearHeading(new Pose2d(41.0, 49.0, Math.toRadians(30)))
                /*.forward(30)
                .waitSeconds(.5)
                .strafeLeft(30)
                .waitSeconds(.5)
                .back(30)
                .waitSeconds(.5)
                .strafeRight(30)
                .waitSeconds(.5)*/
                .build();

        drive.followTrajectorySequence(traj);

        sleep(2000);

        /*drive.followTrajectorySequence(
                drive.trajectorySequenceBuilder(traj.end(), true)
                        .splineTo(new Vector2d(0, 0), Math.toRadians(180))
                        .build()
        );*/
    }
}
