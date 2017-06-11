package com.taotao.rest.controller;

import com.mysql.jdbc.StringUtils;
import com.taotao.rest.pojo.ItemCatResult;
import com.taotao.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>Title:com.taotao.rest.controller</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/9.
 */
@Controller
@RequestMapping("item/cat")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

//    @RequestMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
//    @ResponseBody
//    public String getItemCatList(String callback) {
//        ItemCatResult result = itemCatService.getItemCatList();
//        if (StringUtils.isEmptyOrWhitespaceOnly(callback)) {
//            //需要把result转换成字符串
//            String json = JsonUtils.objectToJson(result);
//            return json;
//        }
//        //如果字符串不为空，需要指出jsonp调用
//        //需要把result转换成字符串
//        String json = JsonUtils.objectToJson(result);
//        return callback + "(" + json + ");";
//    }

    //必须有请求头格式设置否则报错,produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8"
    @RequestMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    @ResponseBody
    public Object getItemCatList(String callback) {
        ItemCatResult result = itemCatService.getItemCatList();
        if (StringUtils.isEmptyOrWhitespaceOnly(callback)) {
            //需要把result转换成字符串
            return result;
        }
        //如果字符串不为空，需要指出jsonp调用
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
    }

//    @RequestMapping("/list")
//    @ResponseBody
//    public ItemCatResult getItemCatList(){
//        ItemCatResult itemCatList = itemCatService.getItemCatList();
//        return itemCatList;
//    }
}
