package org.firstinspires.ftc.teamcode.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

/*
 * This is an example of a more complex path to really test the tuning.
 */
@Autonomous(group = "drive")
public class SplineTest4 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        RevIMU imu = new RevIMU(hardwareMap);
        imu.init();
        waitForStart();

        if (isStopRequested()) return;

        Trajectory traj = drive.trajectoryBuilder(new Pose2d(0, 63.0, 1.5707963267948966))
                //.strafeRight(10)
                //.forward(30)
                .lineToLinearHeading(new Pose2d(-52.0, 53.0, 1.5707963267948966))
                .lineToLinearHeading(new Pose2d(-57.0, 59.1, 1.5707963267948966 + Math.toRadians(-40)))
                .lineToLinearHeading(new Pose2d(-28.7, 39.1, 1.5707963267948966+Math.toRadians(46)))
                .lineToLinearHeading(new Pose2d(-30.0, 42.0, 1.5707963267948966+Math.toRadians(46)))
                .lineToLinearHeading(new Pose2d(-54.0, 45.0, 1.5707963267948966+Math.toRadians(0)))
                .build();




        drive.followTrajectory(traj);

        sleep(2000);
/*
        drive.followTrajectory(
                drive.trajectoryBuilder(traj.end(), true)
                        .splineTo(new Vector2d(0, 0), Math.toRadians(180))
                        .build()
        );*/
    }
}
