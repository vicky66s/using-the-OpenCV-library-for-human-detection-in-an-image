import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;

public class HumanDetection {
    public static void main(String[] args) {
        // Load OpenCV library24302
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Load the pre-trained Haar cascade classifier for human detection
        CascadeClassifier detector = new CascadeClassifier("haarcascade_upperbody.xml");

        // Read the input image.52
        Mat img = Imgcodecs.imread("image.jpg");

        // Convert the image to grayscale
        Mat grayImg = new Mat();
        Imgproc.cvtColor(img, grayImg, Imgproc.COLOR_BGR2GRAY);

        // Detect human(s) in the image
        MatOfRect bbox = new MatOfRect();
        detector.detectMultiScale(grayImg, bbox);

        // Insert bounding boxes around detected human(s)
        Rect[] boxesArray = bbox.toArray();
        for (Rect box : boxesArray) {
            Imgproc.rectangle(img, box.tl(), box.br(), new Scalar(0, 255, 0), 3);
            Imgproc.putText(img, "Human", new Point(box.x, box.y - 10), Core.FONT_HERSHEY_SIMPLEX, 1, new Scalar(0, 255, 0), 2);
        }

        // Display the result
        HighGui.imshow("Detected Humans", img);
        HighGui.waitKey(0);
        HighGui.destroyAllWindows();
    }
}
