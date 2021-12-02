import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;
import org.firstinspires.ftc.robotcore.external.Telemetry;
public class Tester extends OpenCvPipeline {
    
    public Scalar lower = new Scalar(0, 0, 0);
    public Scalar upper = new Scalar(255, 255, 83);
    
    private Mat ycrcbMat = new Mat();
    private Mat binaryMat = new Mat();
    private Mat maskedInputMat = new Mat();

    Telemetry telemetry;
    public Tester(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    @Override
    public Mat processFrame(Mat input) {

        Imgproc.cvtColor(input, ycrcbMat, Imgproc.COLOR_RGB2YCrCb);

        Core.inRange(ycrcbMat, lower, upper, binaryMat);

        maskedInputMat.release();

        Core.bitwise_and(input, input, maskedInputMat, binaryMat);

        telemetry.addData("[Hello]", "World!");
        telemetry.update();
        

        return maskedInputMat;
    }
}