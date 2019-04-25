package com.moon.studentplatform.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Moon
 */
@Controller
public class HalloController {

    @RequestMapping("/hello")
    public String index(ModelMap map) {
        int i = 1 / 0;
        map.addAttribute("host", "www.abc.com");
        return "hello";
    }
}
