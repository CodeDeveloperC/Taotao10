package com.taotao.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Title:com.taotao.sso.controller</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/13.
 */
@Controller
public class PageController {
    @RequestMapping("/page/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/page/login")
    public String login(String redirectURL, Model model) {
        model.addAttribute("redirect", redirectURL);
        System.out.println("redirectURL:" + redirectURL);
        return "login";
    }
}
