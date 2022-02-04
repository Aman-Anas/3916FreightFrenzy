package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

/**
 * Autonomous Path
 * @author Aman Anas
 * @author Nathan Battle
 */

@Autonomous(name="Red_2_NoCycle", group="Apex Robotics 3916")
public class Red_2_NoCycle extends LinearOpMode {

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
        TrajectorySequence traj1 = drive.trajectorySequenceBuilder(new Pose2d(12.5, -63.0, 1.5707963267948966))
                .lineToLinearHeading(new Pose2d(-55.0, -53.0, 1.5707963267948966))
                .build();

        TrajectorySequence traj2 = drive.trajectorySequenceBuilder(new Pose2d(-55.0, -53.0, 1.5707963267948966))
                .lineToSplineHeading(new Pose2d(-11.0, -43.0, -1.5707963267948966))
                .build();

        TrajectorySequence traj3 = drive.trajectorySequenceBuilder(new Pose2d(-11.0, -43.0, -1.5707963267948966))
                .splineToSplineHeading(new Pose2d(8.0, -45.0, 0.0), 0.0)
                .splineToSplineHeading(new Pose2d(38.0, -45.0, 0.0), 0.0)
                .lineToLinearHeading(new Pose2d(41.0, -49.0, Math.toRadians(-30)))
                .build();

        TrajectorySequence traj4 = drive.trajectorySequenceBuilder(new Pose2d(41.0, -49.0, Math.toRadians(-30)))
                .splineToLinearHeading(new Pose2d(39.0, -38.0, 0.0), 0.0)
                .lineToConstantHeading(new Vector2d(60.0, -38.0))
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
            bot.runDuckMotor(1);
            sleep(2000);
            bot.runDuckMotor(0);
            drive.followTrajectorySequence(traj2);
            // drop off freight
            bot.deliverFreight();
            // drive
            drive.followTrajectorySequence(traj3);
            // pick up freight and park
            bot.resetSlide();
            bot.runIntakeMotor(1);
            sleep(1000);
            bot.runIntakeMotor(0);
            drive.followTrajectorySequence(traj4);

            //wait this long after move
            sleep(2000);
            //stop OpMode
            requestOpModeStop();
        }


    }
}