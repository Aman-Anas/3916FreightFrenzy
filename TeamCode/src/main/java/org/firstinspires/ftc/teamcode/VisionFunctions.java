package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.hardware.HardwareMap;


import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

public class VisionFunctions {

    public HardwareMap hardwareMap;
    public OpenCvCamera extCam;
    public ObjectDeterminationPipeline pipeline;

    public void initVision (HardwareMap hw) {
        this.hardwareMap = hw;
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        WebcamName webcamName = hardwareMap.get(WebcamName.class,"webcam");
        extCam = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);


        pipeline = new ObjectDeterminationPipeline();
        extCam.setPipeline(pipeline);
        extCam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                extCam.startStreaming(320,240, OpenCvCameraRotation.SIDEWAYS_LEFT);
                FtcDashboard.getInstance().startCameraStream(extCam,30);
            }
        });
    }

    public double getAnalysis () {
        return pipeline.getAnalysis();
    }
    public String getPosition (){
        return pipeline.position.toString();
    }



    public static class ObjectDeterminationPipeline extends OpenCvPipeline
    {

        /*
         * An enum to define the possible positions
         */
        public enum Position
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
        private volatile Position position = Position.FOUR;

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



            Point region1_pointA = new Point(
                    REGION1_TOPLEFT_ANCHOR_POINT.x,
                    REGION1_TOPLEFT_ANCHOR_POINT.y);
            Point region1_pointB = new Point(
                    REGION1_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
                    REGION1_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);


            inputToCb(input);

            avg1 = (int) Core.mean(region1_Cb).val[0];

            Imgproc.rectangle(
                    input, // Buffer to draw on
                    region1_pointA, // First point which defines the rectangle
                    region1_pointB, // Second point which defines the rectangle
                    BLUE, // The color the rectangle is drawn in
                    2); // Thickness of the rectangle lines

            position = Position.FOUR; // Record our analysis
            if(avg1 > FOUR_RING_THRESHOLD){
                position = Position.FOUR;
            }else if (avg1 > ONE_RING_THRESHOLD){
                position = Position.ONE;
            }else{
                position = Position.NONE;
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
