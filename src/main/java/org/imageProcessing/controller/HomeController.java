package org.imageProcessing.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author saniaky
 * @since 9/2/14
 */
@Controller
public class HomeController {

    private static final Logger log = Logger.getLogger(HomeController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showHomePage(HttpServletRequest request) {
        log.info("show home page");
        return "index";
    }

}
