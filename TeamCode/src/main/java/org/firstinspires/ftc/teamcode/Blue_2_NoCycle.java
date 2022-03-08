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

@Autonomous(name="Blue_2_NoCycle", group="Apex Robotics 3916")
public class Blue_2_NoCycle extends LinearOpMode {

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
        TrajectorySequence traj1 = drive.trajectorySequenceBuilder(new Pose2d(-30, 63.0, 1.5707963267948966))
                .lineToLinearHeading(new Pose2d(-46.0, 53.0, 1.5707963267948966))

                .lineToLinearHeading(new Pose2d(-50.0, 68.0, 1.5707963267948966 + Math.toRadians(-55)))

                .build();

        TrajectorySequence traj2 = drive.trajectorySequenceBuilder(new Pose2d(-50.0, 68.0, 1.5707963267948966 + Math.toRadians(-55)))
                .lineToLinearHeading(new Pose2d(-29.9, 29.7, 1.5707963267948966+Math.toRadians(40)))
                .build();
        TrajectorySequence traj22 = drive.trajectorySequenceBuilder(new Pose2d(-29.9, 29.7, 1.5707963267948966+Math.toRadians(40)))
                .lineToLinearHeading(new Pose2d(-35.0, 39.60, 1.5707963267948966+Math.toRadians(46)))
                .build();
        TrajectorySequence traj3 = drive.trajectorySequenceBuilder(new Pose2d(-35.0, 39.60, 1.5707963267948966+Math.toRadians(46)))
                .lineToLinearHeading(new Pose2d(-68.50, 47.5, 1.5707963267948966+Math.toRadians(-20)))
                .build();
/*
        TrajectorySequence traj4 = drive.trajectorySequenceBuilder(new Pose2d(41.0, 49.0, Math.toRadians(30)))
                .splineToLinearHeading(new Pose2d(39.0, 38.0, 0.0), 0.0)
                .lineToConstantHeading(new Vector2d(60.0, 38.0))
                .build();

*/

        //Wait until the driver presses start
        waitForStart();

        //Cache the camera analysis and print it in telemetry
        //double objAnalysis = ringPipeline.getAnalysis();
        //String objPosition = ringPipeline.position.toString();
        //telemetry.addData("Analysis: ", objAnalysis);
        //telemetry.addData("Position: ", objPosition);
        //telemetry.update();

        while (opModeIsActive() && !isStopRequested()){
            bot.runIntakeArmServo(TeleOpConfig.GATE_SERVO_MIN);
            bot.runIntakeBucketServo(TeleOpConfig.BUCKET_SERVO_MIN);
            //Follow the trajectory we defined earlier
            drive.followTrajectorySequence(traj1);

            bot.runDuckMotor(1);
            sleep(3000);
            bot.runDuckMotor(0);
            // drive

        drive.followTrajectorySequence(traj2);
            // drop off freight
            //bot.deliverFreight();
            bot.runIntakeBucketServo(TeleOpConfig.BUCKET_SERVO_MIN);
            bot.runIntakeMotor(1);
            bot.runSlideMotor(1);
            sleep(1300);
            bot.runSlideMotor(0);
            sleep(500);
            bot.runIntakeArmServo(TeleOpConfig.GATE_SERVO_MAX);
            sleep(500);
            bot.runIntakeBucketServo(TeleOpConfig.BUCKET_SERVO_MAX);
            bot.runIntakeArmServo(TeleOpConfig.GATE_SERVO_MIN);
            drive.followTrajectorySequence(traj22);
            bot.runSlideMotor(-1);
            bot.runIntakeMotor(0);
            sleep(1050);

            bot.runSlideMotor(0);

            // drive
            drive.followTrajectorySequence(traj3);
            // pick up freight and park
            //bot.runIntakeMotor(1);
            //sleep(1000);
            //bot.runIntakeMotor(0);
            //drive.followTrajectorySequence(traj4);

            //wait this long after move
            sleep(2000);
            //stop OpMode
            requestOpModeStop();
        }


    }
}