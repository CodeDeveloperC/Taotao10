package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>description: 页面跳转</p>
 *
 * @Author: 司马懿
 * @data: 2017/6/6.
 */
@Controller
public class PageController {
    //展示首页
    @RequestMapping("/")
    public String showIndex() {
        return "index";
    }

    //展示功能页面

    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page) {
        return page;
    }
}
