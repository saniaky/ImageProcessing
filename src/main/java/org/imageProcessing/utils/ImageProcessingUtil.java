package org.imageProcessing.utils;

import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author saniaky
 * @since 9/7/14
 */
@Service
public interface ImageProcessingUtil {

    public File GaussianBlur(File file, int kernelSize, int sigmaX);

    public File SobelOperator(File file, int dx, int dy);

    public File LaplacianOperator(File file, int ksize, double scale, double delta);

}
