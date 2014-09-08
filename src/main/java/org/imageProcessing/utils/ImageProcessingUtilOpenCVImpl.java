package org.imageProcessing.utils;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.imageProcessing.controller.FileController;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author saniaky
 * @since 9/7/14
 */
@Service
public class ImageProcessingUtilOpenCVImpl implements ImageProcessingUtil {

    private static final Logger log = Logger.getLogger(FileController.class);

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    @Override
    public File GaussianBlur(File file, int kernelSize, int sigmaX) {
        String ext = FilenameUtils.getExtension(file.getPath());
        String name = file.getParent() + "/" + String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        Mat source = Highgui.imread(file.getPath(), Highgui.CV_LOAD_IMAGE_ANYCOLOR);
        Mat destination = new Mat(source.rows(), source.cols(), source.type());
        Imgproc.GaussianBlur(source, destination, new Size(kernelSize, kernelSize), sigmaX);
        Highgui.imwrite(name, destination);
        return new File(name);
    }

    @Override
    public File SobelOperator(File file, int dx, int dy) {
        String ext = FilenameUtils.getExtension(file.getPath());
        String name = file.getParent() + "/" + String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        Mat source = Highgui.imread(file.getPath(), Highgui.CV_LOAD_IMAGE_ANYCOLOR);
        Mat destination = new Mat(source.rows(), source.cols(), source.type());
        Imgproc.Sobel(source, destination, -1, dx, dy);
        Highgui.imwrite(name, destination);
        return new File(name);
    }

    @Override
    public File LaplacianOperator(File file, int ksize, double scale, double delta) {
        String ext = FilenameUtils.getExtension(file.getPath());
        String name = file.getParent() + "/" + String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        Mat source = Highgui.imread(file.getPath(), Highgui.CV_LOAD_IMAGE_ANYCOLOR);
        Mat destination = new Mat(source.rows(), source.cols(), source.type());
        Imgproc.Laplacian(source, destination, -1, ksize, scale, delta);
        Highgui.imwrite(name, destination);
        return new File(name);
    }

}
