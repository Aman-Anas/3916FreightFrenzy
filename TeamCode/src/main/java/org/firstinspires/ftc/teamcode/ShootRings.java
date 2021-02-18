package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

/*
 * Copyright (c) 2020 OpenFTC Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */





@Autonomous
public class ShootRings extends LinearOpMode
{

    SkystoneDeterminationPipeline pipeline;
    FTCLibRobotFunctions bot = new FTCLibRobotFunctions();

    @Override
    public void runOpMode()
    {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        WebcamName webcamName = hardwareMap.get(WebcamName.class,"webcam");
        OpenCvCamera extCam = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);

        /*phoneCam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "webcam"), cameraMonitorViewId);
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        phoneCam = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);*/

        pipeline = new SkystoneDeterminationPipeline();
        extCam.setPipeline(pipeline);

        // We set the viewport policy to optimized view so the preview doesn't appear 90 deg
        // out when the RC activity is in portrait. We do our actual image processing assuming
        // landscape orientation, though. !Edit by Aman: Since we're using an external webcam, this is not applicable
        //extCam.setViewportRenderingPolicy(OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW);

        extCam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                extCam.startStreaming(320,240, OpenCvCameraRotation.SIDEWAYS_LEFT);
                FtcDashboard.getInstance().startCameraStream(extCam,30);
            }
        });
        //SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        bot.initBot(hardwareMap);
        waitForStart();

        while (opModeIsActive())
        {
            telemetry.addData("Analysis", pipeline.getAnalysis());
            //elemetry.addData("Position", pipeline.position);




            sleep(3000);
            SkystoneDeterminationPipeline.RingPosition current = pipeline.position;
            telemetry.addData("Position", current);
            telemetry.update();
            Trajectory traj = drive.trajectoryBuilder(new Pose2d(-62.0, -50, 0)).build();
            Pose2d startPose;
            if (current == SkystoneDeterminationPipeline.RingPosition.FOUR) {

                if (isStopRequested()) return;
                startPose = new Pose2d(-62.0, -50, 0);
                drive.setPoseEstimate(startPose);
                traj = drive.trajectoryBuilder(new Pose2d(-62.0, -50, 0), 0)
                        .splineToSplineHeading(new Pose2d(-61.99, -50, 0.0), Math.toRadians(-10.0))
                        .splineToSplineHeading(new Pose2d(53.0, -60.0), 0.0)
                        .splineToSplineHeading(new Pose2d(10.0, -60.0), 0.0)
                        .build();

                drive.followTrajectory(traj);
            }

            else if (current == SkystoneDeterminationPipeline.RingPosition.ONE){

                if (isStopRequested()) return;
                startPose = new Pose2d(-62.0, -50, 0);
                drive.setPoseEstimate(startPose);

                traj = drive.trajectoryBuilder(new Pose2d(-62.0, -50, 0), 0)
                        .splineToSplineHeading(new Pose2d(-61.99, -50), Math.toRadians(-20))
                        .splineToSplineHeading(new Pose2d(26, -40), 0)
                        .splineToLinearHeading(new Pose2d(8, -37), 0)
                        .build();

                drive.followTrajectory(traj);
            }

            else if (current == SkystoneDeterminationPipeline.RingPosition.NONE){
                if (isStopRequested()) return;
                startPose = new Pose2d(-62.0, -50, 0);
                drive.setPoseEstimate(startPose);
                traj = drive.trajectoryBuilder(new Pose2d(-62.0, -50, 0), 0)
                        .splineToSplineHeading(new Pose2d(-61.99, -50), Math.toRadians(-10))
                        .splineToSplineHeading(new Pose2d(-5, -60), 0)
                        .splineToConstantHeading(new Vector2d(-20, -50), Math.toRadians(10))
                        .splineToLinearHeading(new Pose2d(4, -20), Math.toRadians(-20))
                        .build();
                drive.followTrajectory(traj);


            }
            bot.setFlywheelMotor(0.8);
            Trajectory moveShoot = drive.trajectoryBuilder(traj.end())
                    .splineToConstantHeading(new Vector2d(-13.5,-43.5),Math.toRadians(0))
                    .build();
            drive.followTrajectory(moveShoot);

            ElapsedTime savedTime = new ElapsedTime (ElapsedTime.Resolution.MILLISECONDS);
            savedTime.reset();
            while (savedTime.time() < 2000){
                bot.setFlywheelMotor(0.8);
            }
            savedTime.reset();
            while (savedTime.time() < 2000){
                bot.runTransferServo(1.0);
            }
            savedTime.reset();
            while (savedTime.time()<2000){
                bot.runTransferServo(-1.0);
            }

            Trajectory moveForward = drive.trajectoryBuilder(moveShoot.end())
                    .splineTo(new Vector2d(3,-40),Math.toRadians(0))
                    .build();
            drive.followTrajectory(moveForward);
            bot.setFlywheelMotor(0.0);







            // Don't burn CPU cycles busy-looping in this sample
            sleep(20000);
        }

    }


    public static class SkystoneDeterminationPipeline extends OpenCvPipeline
    {
        /*
         * An enum to define the ring position
         */
        public enum RingPosition
        {
            FOUR,
            ONE,
            NONE
        }

        /*
         * Some color constants
         */
        static final Scalar BLUE = new Scalar(0, 0, 255);
        static final Scalar GREEN = new Scalar(0, 255, 0);

        /*
         * The core values which define the location and size of the sample regions
         */
        public static Point REGION1_TOPLEFT_ANCHOR_POINT = new Point(TeleOpConfig.ANCHOR_POINT_X,TeleOpConfig.ANCHOR_POINT_Y);

        public static int REGION_WIDTH = (int)TeleOpConfig.REGION_WIDTH_X;
        public static int REGION_HEIGHT = (int)TeleOpConfig.REGION_HEIGHT_Y;

        public int FOUR_RING_THRESHOLD = (int)TeleOpConfig.FOUR_RING_THRESHOLD_CONFIG;
        public int ONE_RING_THRESHOLD = (int)TeleOpConfig.ONE_RING_THRESHOLD_CONFIG;

        Point region1_pointA = new Point(
                REGION1_TOPLEFT_ANCHOR_POINT.x,
                REGION1_TOPLEFT_ANCHOR_POINT.y);
        Point region1_pointB = new Point(
                REGION1_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
                REGION1_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);

        /*
         * Working variables
         */
        Mat region1_Cb;
        Mat YCrCb = new Mat();
        Mat Cb = new Mat();
        int avg1;

        // Volatile since accessed by OpMode thread w/o synchronization
        public volatile RingPosition position = RingPosition.FOUR;

        /*
         * This function takes the RGB frame, converts to YCrCb,
         * and extracts the Cb channel to the 'Cb' variable
         */
        void inputToCb(Mat input)
        {
            Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
            Core.extractChannel(YCrCb, Cb, 1);
        }

        @Override
        public void init(Mat firstFrame)
        {
            inputToCb(firstFrame);

            region1_Cb = Cb.submat(new Rect(region1_pointA, region1_pointB));
        }

        @Override
        public Mat processFrame(Mat input)
        {
            inputToCb(input);

            avg1 = (int) Core.mean(region1_Cb).val[0];

            Imgproc.rectangle(
                    input, // Buffer to draw on
                    region1_pointA, // First point which defines the rectangle
                    region1_pointB, // Second point which defines the rectangle
                    BLUE, // The color the rectangle is drawn in
                    2); // Thickness of the rectangle lines

            position = RingPosition.FOUR; // Record our analysis
            if(avg1 > FOUR_RING_THRESHOLD){
                position = RingPosition.FOUR;
            }else if (avg1 > ONE_RING_THRESHOLD){
                position = RingPosition.ONE;
            }else{
                position = RingPosition.NONE;
            }

            Imgproc.rectangle(
                    input, // Buffer to draw on
                    region1_pointA, // First point which defines the rectangle
                    region1_pointB, // Second point which defines the rectangle
                    GREEN, // The color the rectangle is drawn in
                    -1); // Negative thickness means solid fill

            return input;
        }

        public int getAnalysis()
        {
            return avg1;
        }
    }
}