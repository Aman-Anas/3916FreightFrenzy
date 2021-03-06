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

@Autonomous(name="Red_2_NoDuck", group="Apex Robotics 3916")
public class Red_2_NoDuck extends LinearOpMode {

    //CameraFunctions botCamera = new CameraFunctions();
    //RingDeterminationPipeline ringPipeline = new RingDeterminationPipeline();

    @Override
    public void runOpMode() throws InterruptedException {

        //Initialize telemetry and dashboard
        //telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        FtcDashboard dashboard = FtcDashboard.getInstance();

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        FTCLibRobotFunctions bot = new FTCLibRobotFunctions();
        bot.initBot(hardwareMap);

        bot.slideMotor.encoder.reset();

        //Initialize the camera and vision
        //botCamera.initVision(hardwareMap, ringPipeline);

        //Construct trajectories for the robot to follow.
        //https://learnroadrunner.com/trajectorybuilder-functions.html
        TrajectorySequence traj1 = drive.trajectorySequenceBuilder(new Pose2d(12.5, -63.0, -1.5707963267948966))
                .splineToLinearHeading(new Pose2d(10.0, -61.0, -1.5707963267948966), 2.0943951023931953)
                .splineToSplineHeading(new Pose2d(2.0, -40.0, 2.356194490192345), 1.7453292519943295)
                .build();

        TrajectorySequence traj2 = drive.trajectorySequenceBuilder(new Pose2d(2.0, -40.0, 2.356194490192345))
                .splineToLinearHeading(new Pose2d(8.0, -38.0, 2.356194490192345), 0.0)
                .splineToSplineHeading(new Pose2d(10.0, -38.0, 3.141592653589793), 0.0)
                .lineToLinearHeading(new Pose2d(62.0, -38.0, 3.141592653589793))
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
            drive.followTrajectorySequence(traj1);
            // deliver freight
            bot.deliverFreight();
            bot.resetSlide();
            // drive
            drive.followTrajectorySequence(traj2);

            //wait this long after move
            sleep(2000);
            //stop OpMode
            requestOpModeStop();
        }


    }
}