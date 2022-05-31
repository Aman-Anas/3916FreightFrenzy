package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.vision.CameraFunctions;
import org.firstinspires.ftc.teamcode.vision.RingDeterminationPipeline;

/**
 * Autonomous Path
 * @author Aman Anas
 * @author Nathan Battle
 */
@Disabled
@Autonomous(name="Blue_1_Cycle", group="Apex Robotics 3916")
public class Blue_1_Cycle extends LinearOpMode {

    CameraFunctions botCamera = new CameraFunctions();
    RingDeterminationPipeline ringPipeline = new RingDeterminationPipeline();

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
        botCamera.initVision(hardwareMap, ringPipeline);

        //Construct trajectories for the robot to follow.
        //https://learnroadrunner.com/trajectorybuilder-functions.html
        Pose2d startPose = new Pose2d(12, 63, Math.toRadians(90));

        TrajectorySequence traj1 = drive.trajectorySequenceBuilder(startPose)
                .lineToLinearHeading(new Pose2d(-55.0, 53.0, Math.toRadians(90)))
                .build();

        TrajectorySequence traj2 = drive.trajectorySequenceBuilder(traj1.end())
                .lineToLinearHeading(new Pose2d(-11.0, 43.0, Math.toRadians(90)))
                .build();

        TrajectorySequence traj3 = drive.trajectorySequenceBuilder(traj2.end())
                .splineToLinearHeading(new Pose2d(8.0, 45.0, 0.0), 0.0)
                .splineToLinearHeading(new Pose2d(38.0, 45.0, 0.0), 0.0)
                .lineToLinearHeading(new Pose2d(41.0, 49.0, Math.toRadians(30)))
                .build();

        TrajectorySequence traj4 = drive.trajectorySequenceBuilder(traj3.end())
                .splineToLinearHeading(new Pose2d(38.0, 45.0, 0.0), 0.0)
                .lineToLinearHeading(new Pose2d(8.0, 45.0, 0.0))
                .lineToLinearHeading(new Pose2d(3, 37.0, Math.toRadians(45)))
                .build();

        TrajectorySequence traj5 = drive.trajectorySequenceBuilder(traj4.end())
                .splineToLinearHeading(new Pose2d(8.0, 45.0, 0.0), 0.0)
                .splineToLinearHeading(new Pose2d(38.0, 45.0, 0.0), 0.0)
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
            drive.setPoseEstimate(startPose);
            drive.followTrajectorySequence(traj1);
           // bot.runDuckMotor(1);
            sleep(2000);
            //bot.runDuckMotor(0);
            drive.followTrajectorySequence(traj2);
            // deliver freight
            bot.deliverFreight();
            bot.resetSlide();
            // drive
            drive.followTrajectorySequence(traj3);
            // pick up freight
            bot.runIntakeMotor(1);
            sleep(1000);
            bot.runIntakeMotor(0);
            // drive
            drive.followTrajectorySequence(traj4);
            // deliver freight
            bot.deliverFreight();
            bot.resetSlide();
            // drive
            drive.followTrajectorySequence(traj5);
            // pick up freight
            bot.runIntakeMotor(1);
            sleep(1000);
            bot.runIntakeMotor(0);
            // drive
            drive.followTrajectorySequence(traj4);
            // deliver freight
            bot.deliverFreight();
            bot.resetSlide();
            // drive
            drive.followTrajectorySequence(traj5);
            // pick up freight
            bot.runIntakeMotor(1);
            sleep(1000);
            bot.runIntakeMotor(0);
            // drive
            drive.followTrajectorySequence(traj4);
            // deliver freight
            bot.deliverFreight();
            bot.resetSlide();
            // drive
            drive.followTrajectorySequence(traj5);
            // pick up freight
            bot.runIntakeMotor(1);
            sleep(1000);
            bot.runIntakeMotor(0);

            //wait this long after move
            sleep(2000);
            //stop OpMode
            requestOpModeStop();
        }


    }
}