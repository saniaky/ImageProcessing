package org.imageProcessing.controller;

import org.apache.log4j.Logger;
import org.imageProcessing.model.ImageFile;
import org.imageProcessing.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;

/**
 * @author saniaky
 * @since 9/3/14
 */
@Controller
public class FileController {

    private static final Logger log = Logger.getLogger(FileController.class);

    @Value("${images.path}")
    private String imagesPath;

    @Autowired
    FileService fileService;

    @RequestMapping(value = "/getImage", method = RequestMethod.GET)
    @ResponseBody
    public String getImage(HttpServletRequest request, HttpSession session) {
        File image = (File) session.getAttribute("image");
        if (image != null) {
            return image.getName();
        }
        return null;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(ImageFile imageFile, HttpServletRequest request, HttpSession session) {
        log.info("upload");
        // TODO check for image type
        String webAppRoot = request.getServletContext().getRealPath("/");
        String path = webAppRoot + "/" + imagesPath;
        File file = fileService.saveFile(imageFile.getFile(), path);
        session.setAttribute("image", file);
        return file.getName();
    }

}
