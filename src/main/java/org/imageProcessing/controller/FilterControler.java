package org.imageProcessing.controller;

import org.apache.log4j.Logger;
import org.imageProcessing.service.FileService;
import org.imageProcessing.utils.ImageProcessingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Map;

/**
 * @author saniaky
 * @since 9/7/14
 */
@Controller
public class FilterControler {

    private static final Logger log = Logger.getLogger(FileController.class);

    @Autowired
    FileService fileService;

    @Autowired
    ImageProcessingUtil imageProcessingUtil;

    @RequestMapping(value = "/applyFilter", method = RequestMethod.GET)
    @ResponseBody
    public String applyFilter(@RequestParam Map<String, String> filter, HttpSession session) {
        File image = (File) session.getAttribute("image");
        File newImage = null;
        if (image != null) {
            if (filter.get("name").equals("gauss")) {
                int kernel = Integer.valueOf(filter.get("kernel"));
                int sigmaX = Integer.valueOf(filter.get("sigmaX"));
                newImage = imageProcessingUtil.GaussianBlur(image, kernel, sigmaX);
                return newImage.getName();
            } else if (filter.get("name").equals("sobel")) {
                int dx = Integer.valueOf(filter.get("dx"));
                int dy = Integer.valueOf(filter.get("dy"));
                newImage = imageProcessingUtil.SobelOperator(image, dx, dy);
                return newImage.getName();
            } else {
                int ksize = Integer.valueOf(filter.get("ksize"));
                double scale = Double.valueOf(filter.get("scale"));
                double delta = Double.valueOf(filter.get("delta"));
                newImage = imageProcessingUtil.LaplacianOperator(image, ksize, scale, delta);
                return newImage.getName();
            }
        }
        return null;
    }

}
