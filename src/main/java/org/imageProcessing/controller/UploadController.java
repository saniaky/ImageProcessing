package org.imageProcessing.controller;

import org.apache.log4j.Logger;
import org.imageProcessing.model.ImageFile;
import org.imageProcessing.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;

/**
 * @author saniaky
 * @since 9/3/14
 */
@Controller
public class UploadController {

    private static final Logger logger = Logger.getLogger(UploadController.class);

    @Autowired
    FileService fileService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(ImageFile imageFile, HttpServletRequest request, HttpSession session) {
        logger.info("upload");
        if (session.getAttribute("image") == null) {
            String webappRoot = request.getServletContext().getRealPath("/");
            String path = webappRoot + "/images";
            boolean dir = new File(path).mkdir();
            File file = fileService.saveFile(imageFile.getFile(), path);
            session.setAttribute("image", file);
        }
        return ((File)session.getAttribute("image")).getName();
    }

}
