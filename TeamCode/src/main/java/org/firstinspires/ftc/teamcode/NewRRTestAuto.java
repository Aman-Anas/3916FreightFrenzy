package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

/**
 * Autonomous Path
 * @author Aman Anas
 * @author Nathan Battle
 */

@Autonomous(name="NEW RR TEST AUTO", group="Apex Robotics 3916")
public class NewRRTestAuto extends LinearOpMode {

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

        //bot.slideMotor.encoder.reset();

        //Initialize the camera and vision
        //botCamera.initVision(hardwareMap, ringPipeline);
        waitForStart();
        drive.setPoseEstimate(new Pose2d());
        //Construct trajectories for the robot to follow.
        //https://learnroadrunner.com/trajectorybuilder-functions.html
        TrajectorySequence traj = drive.trajectorySequenceBuilder(new Pose2d())
                .splineTo(new Vector2d(30, 30), 0)
                .build();



        //Wait until the driver presses start

        //Cache the camera analysis and print it in telemetry
        //double objAnalysis = ringPipeline.getAnalysis();
        //String objPosition = ringPipeline.position.toString();
        //telemetry.addData("Analysis: ", objAnalysis);
        //telemetry.addData("Position: ", objPosition);
        //telemetry.update();

        while (opModeIsActive() && !isStopRequested()){


            drive.followTrajectorySequence(traj);


            // drive


            sleep(2000);
            //stop OpMode
            requestOpModeStop();
        }


    }
}