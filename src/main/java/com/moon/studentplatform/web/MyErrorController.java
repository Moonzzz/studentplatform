package com.moon.studentplatform.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Moon
 */
@Controller
public class MyErrorController implements ErrorController {
    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        ModelAndView mav = new ModelAndView();

        mav.addObject("statusCode", statusCode)
                .addObject("url", request.getAttribute("javax.servlet.error.request_uri"))
                .setViewName("/error/404");
        return mav;

    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
