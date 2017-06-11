package com.taotao.portal.controller;

import com.taotao.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>Title:com.taotao.portal.controller</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/9.
 */
@Controller
public class IndexController {
    @Autowired
    private ContentService contentService;

    @RequestMapping("/index")
    public String showIndex(Model model){
        String json = contentService.getAdList();
        model.addAttribute("ad1", json);
        return "index";
    }

    @RequestMapping(value = "/testhttppost",method = RequestMethod.POST)
    @ResponseBody
    public String testHttpPost(String name,String pass){
        System.out.println(name);
        System.out.println(pass);
        return "OK";
    }
}
