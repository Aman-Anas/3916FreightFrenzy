package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

/**
 * Autonomous Path
 * @author Aman Anas
 * @author Nathan Battle
 */
//@Disabled
@Autonomous(name="Blue_1_NoCycle", group="Apex Robotics 3916")
public class Blue_1_NoCycle extends LinearOpMode {

    //CameraFunctions botCamera = new CameraFunctions();
    //RingDeterminationPipeline ringPipeline = new RingDeterminationPipeline();

    @Override
    public void runOpMode() throws InterruptedException {

        //Initialize telemetry and dashboard
        //telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        FtcDashboard dashboard = FtcDashboard.getInstance();

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        FTCLibRobotFunctions bot = new FTCLibRobotFunctions();
        bot.initBot(hardwareMap, true);

        bot.slideMotor.encoder.reset();

        //Initialize the camera and vision
        //botCamera.initVision(hardwareMap, ringPipeline);

        //Construct trajectories for the robot to follow.
        //https://learnroadrunner.com/trajectorybuilder-functions.html
        Pose2d startPose = new Pose2d(12, 63, Math.toRadians(90));

        TrajectorySequence traj1 = drive.trajectorySequenceBuilder(startPose)
                .lineToLinearHeading(new Pose2d(-52.0, 53.0, Math.toRadians(90)))
                .lineToLinearHeading(new Pose2d(-57.0, 59.1, Math.toRadians(40)),
                        SampleMecanumDrive.getVelocityConstraint(15, DriveConstants.MAX_ANG_VEL,DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(15))
                .build();

        TrajectorySequence traj2 = drive.trajectorySequenceBuilder(traj1.end())
                .lineToLinearHeading(new Pose2d(-23.7, 33.1, Math.toRadians(90)+Math.toRadians(46)))
                .build();
        TrajectorySequence traj3 = drive.trajectorySequenceBuilder(traj2.end())
                .lineToLinearHeading(new Pose2d(-30.0, 42.0, Math.toRadians(136)))
                .build();
        TrajectorySequence traj4 = drive.trajectorySequenceBuilder(traj3.end())
                .lineToLinearHeading(new Pose2d(-60.0, 27.0, Math.toRadians(90)+Math.toRadians(0)))
                .build();
/*/*
        TrajectorySequence traj5 = drive.trajectorySequenceBuilder(traj3.end())
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
            //bot.runIntakeArmServo(TeleOpConfig.GATE_SERVO_MIN);
            //bot.runIntakeBucketServo(TeleOpConfig.BUCKET_SERVO_MIN);

            //Follow the trajectory we defined earlier
            drive.setPoseEstimate(startPose);
            drive.followTrajectorySequence(traj1);

            bot.duckMotor.set(TeleOpConfig.DUCK_MOTOR_MULTIPLIER);
            //bot.slideMotorController(1.0, true, false);
            //bot.autoTipBucket();
            //bot.slideMotorController(-1.0, true, false);
            //bot.autoTipBucket();
            sleep(2500);
            bot.duckMotor.set(0);
            // drive

            drive.followTrajectorySequence(traj2);
            bot.runIntakeBucketServo(TeleOpConfig.BUCKET_SERVO_MIN);
            while (bot.slideMotor.encoder.getPosition() < TeleOpConfig.SLIDE_MOTOR_MAX) {
                bot.slideState = FTCLibRobotFunctions.SlideState.GOING_UP;
                bot.slideMotorController(1.0, true, false);
                bot.autoTipBucket();
            }
            bot.runIntakeArmServo(TeleOpConfig.GATE_SERVO_MIN);
            sleep(1500);
            bot.runIntakeArmServo(TeleOpConfig.GATE_SERVO_MAX);
            sleep(1500);
            bot.runIntakeArmServo(TeleOpConfig.GATE_SERVO_MIN);
            while (bot.slideMotor.encoder.getPosition() > 0) {
                bot.slideMotorController(-0.5, true, false);
                bot.autoTipBucket();
            }

            // drop off freight
            //bot.deliverFreight();
            /*bot.runIntakeBucketServo(TeleOpConfig.BUCKET_SERVO_MIN);
            bot.runIntakeMotor(1);
            bot.runSlideMotor(1);
            sleep(1350);
            bot.runSlideMotor(0);
            sleep(500);
            bot.runIntakeArmServo(TeleOpConfig.GATE_SERVO_MAX);
            sleep(500);
            bot.runIntakeBucketServo(TeleOpConfig.BUCKET_SERVO_MAX);
            bot.runIntakeArmServo(TeleOpConfig.GATE_SERVO_MIN);*/
            //sleep(1150);
            //drive.followTrajectorySequence(traj3);
            sleep(2000);
            /*bot.runSlideMotor(-1);
            bot.runIntakeMotor(0);


            bot.runSlideMotor(0);*/

            // drive
            drive.followTrajectorySequence(traj4);
            // pick up freight and park
            //bot.runIntakeMotor(1);
            //sleep(1000);
            //bot.runIntakeMotor(0);
            //drive.followTrajectorySequence(traj5);

            //wait this long after move
            sleep(2000);
            //stop OpMode
            requestOpModeStop();
        }


    }
}