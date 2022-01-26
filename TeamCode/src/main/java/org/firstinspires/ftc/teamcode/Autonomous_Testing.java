package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.vision.CameraFunctions;
import org.firstinspires.ftc.teamcode.vision.RingDeterminationPipeline;
import org.opencv.engine.OpenCVEngineInterface;
import org.openftc.easyopencv.OpenCvPipeline;

/**
 * Autonomous example/test class.
 * @author Aman Anas
 */

@Autonomous(name="Autonomous Test", group="Apex Robotics 3916")
public class Autonomous_Testing extends LinearOpMode {

    CameraFunctions botCamera = new CameraFunctions();
    RingDeterminationPipeline ringPipeline = new RingDeterminationPipeline();

    @Override
    public void runOpMode() throws InterruptedException {

        //Initialize telemetry and dashboard
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        FtcDashboard dashboard = FtcDashboard.getInstance();

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        //Initialize the camera and vision
        botCamera.initVision(hardwareMap, ringPipeline);

        //Construct trajectories for the robot to follow.
        //https://learnroadrunner.com/trajectorybuilder-functions.html
        Trajectory traj = drive.trajectoryBuilder(new Pose2d(-62.0, 50, 0), 0)
                .splineToSplineHeading(new Pose2d(-62.01, 50), Math.toRadians(10))
                .splineToSplineHeading(new Pose2d(50, 60), 0)
                .splineToSplineHeading(new Pose2d(10, 60), 0)
                .build();



        //Wait until the driver presses start
        waitForStart();

        //Cache the camera analysis and print it in telemetry
        double objAnalysis = ringPipeline.getAnalysis();
        String objPosition = ringPipeline.position.toString();
        telemetry.addData("Analysis: ", objAnalysis);
        telemetry.addData("Position: ", objPosition);
        telemetry.update();

        while (opModeIsActive() && !isStopRequested()){

            //Follow the trajectory we defined earlier
            drive.followTrajectory(traj);

            //wait this long after move
            sleep(2000);
            //stop OpMode
            requestOpModeStop();
        }


    }
}