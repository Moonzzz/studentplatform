package com.moon.studentplatform.web;

import com.moon.studentplatform.dto.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author Moon
 */
@Controller
public class ShiroController {

    @RequestMapping("/login")
    public String login() {
        return "login/login";
    }

    @RequestMapping("/index")
    public String index() {
        return "/index";
    }

    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }

        return "/login/login";
    }

    @RequestMapping("/admin")
    @ResponseBody
    public String admin() {
        return "success admin";
    }

    @RequestMapping("/403")
    public String unAuthorize() {
        return "/error/403";
    }

    @RequestMapping("/loginUser")
    public String loginUser(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpSession session) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(token);
            User user = (User) subject.getPrincipal();
            session.setAttribute("user", user);

            return "/index";
        } catch (Exception e) {
            System.err.println("验证不通过: {}" + e.getMessage());
            return "/login";
        }
    }
}