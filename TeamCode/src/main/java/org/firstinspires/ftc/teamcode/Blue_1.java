package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.vision.CameraFunctions;
import org.firstinspires.ftc.teamcode.vision.RingDeterminationPipeline;
import org.opencv.engine.OpenCVEngineInterface;
import org.openftc.easyopencv.OpenCvPipeline;

/**
 * Autonomous Path
 * @author Aman Anas
 * @author Nathan Battle
 */

@Autonomous(name="Blue_1", group="Apex Robotics 3916")
public class Blue_1 extends LinearOpMode {

    //CameraFunctions botCamera = new CameraFunctions();
    //RingDeterminationPipeline ringPipeline = new RingDeterminationPipeline();

    @Override
    public void runOpMode() throws InterruptedException {

        //Initialize telemetry and dashboard
        //telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        FtcDashboard dashboard = FtcDashboard.getInstance();

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        //Initialize the camera and vision
        //botCamera.initVision(hardwareMap, ringPipeline);

        //Construct trajectories for the robot to follow.
        //https://learnroadrunner.com/trajectorybuilder-functions.html
        TrajectorySequence traj = drive.trajectorySequenceBuilder(new Pose2d(-35.0, 63.0, -1.5707963267948966))
                .splineToLinearHeading(new Pose2d(-55.0, 53.0, -1.5707963267948966), 0.0)
                .waitSeconds(0.5)
                .splineToSplineHeading(new Pose2d(-11.0, 43.0, 1.5707963267948966), 0.0)
                .splineToSplineHeading(new Pose2d(8.0, 45.0, 0.0), 0.0)
                .splineToSplineHeading(new Pose2d(38.0, 45.0, 0.0), 0.0)
                .lineToLinearHeading(new Pose2d(41.0, 49.0, Math.toRadians(30)))
                .splineToLinearHeading(new Pose2d(38.0, 45.0, 0.0), 0.0)
                .lineToLinearHeading(new Pose2d(8.0, 45.0, 0.0))
                .lineToLinearHeading(new Pose2d(3, 37.0, Math.toRadians(45)))
                .lineToLinearHeading(new Pose2d(8.0, 45.0, 0.0))
                .lineToSplineHeading(new Pose2d(38.0, 45.0, 0.0))
                .lineToLinearHeading(new Pose2d(41.0, 49.0, Math.toRadians(30)))
                .splineToLinearHeading(new Pose2d(38.0, 45.0, 0.0), 0.0)
                .lineToLinearHeading(new Pose2d(8.0, 45.0, 0.0))
                .lineToLinearHeading(new Pose2d(3, 37.0, Math.toRadians(45)))
                .lineToLinearHeading(new Pose2d(8.0, 45.0, 0.0))
                .lineToSplineHeading(new Pose2d(38.0, 45.0, 0.0))
                .lineToLinearHeading(new Pose2d(41.0, 49.0, Math.toRadians(30)))
                .splineToLinearHeading(new Pose2d(38.0, 45.0, 0.0), 0.0)
                .lineToLinearHeading(new Pose2d(8.0, 45.0, 0.0))
                .lineToLinearHeading(new Pose2d(3, 37.0, Math.toRadians(45)))
                .lineToLinearHeading(new Pose2d(8.0, 45.0, 0.0))
                .lineToSplineHeading(new Pose2d(38.0, 45.0, 0.0))
                .lineToLinearHeading(new Pose2d(41.0, 49.0, Math.toRadians(30)))
                .splineToLinearHeading(new Pose2d(38.0, 45.0, 0.0), 0.0)
                .lineToLinearHeading(new Pose2d(8.0, 45.0, 0.0))
                .lineToLinearHeading(new Pose2d(3, 37.0, Math.toRadians(45)))
                .lineToLinearHeading(new Pose2d(8.0, 45.0, 0.0))
                .lineToSplineHeading(new Pose2d(38.0, 45.0, 0.0))
                .lineToLinearHeading(new Pose2d(41.0, 49.0, Math.toRadians(30)))
                .build();



        //Wait until the driver presses start
        waitForStart();

        //Cache the camera analysis and print it in telemetry
        //double objAnalysis = ringPipeline.getAnalysis();
        //String objPosition = ringPipeline.position.toString();
        //telemetry.addData("Analysis: ", objAnalysis);
        //telemetry.addData("Position: ", objPosition);
        //telemetry.update();

        while (opModeIsActive() && !isStopRequested()){

            //Follow the trajectory we defined earlier
            drive.followTrajectorySequence(traj);

            //wait this long after move
            sleep(2000);
            //stop OpMode
            requestOpModeStop();
        }


    }
}