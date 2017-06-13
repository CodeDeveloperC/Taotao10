package com.taotao.portal.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.portal.service.StaticPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>Title:com.taotao.portal.controller</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/13.
 */
@Controller
public class StaticPageController {
    @Autowired
    private StaticPageService staticPageService;

    //可以解决406问题
//    @RequestMapping("/gen/item/{itemId}")
//    @ResponseBody
//    public String getItemHtml(@PathVariable Long itemId) {
//        try {
//            TaotaoResult result = staticPageService.getItemHtml(itemId);
//            String json = JsonUtils.objectToJson(result);
//            return json;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "";
//        }
//    }

    //
    @RequestMapping("/gen/item/{itemId}")
    @ResponseBody
    public TaotaoResult getItemHtml(@PathVariable Long itemId) {
        try {
            TaotaoResult result = staticPageService.getItemHtml(itemId);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}
