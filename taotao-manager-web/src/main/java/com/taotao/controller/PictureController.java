package com.taotao.controller;

import com.taotao.common.pojo.PictureResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>Title:com.taotao.controller</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/8.
 */
@Controller
public class PictureController {
    @Autowired
    private PictureService pictureService;

    //解决Firefox的兼容性问题
    @RequestMapping("/pic/upload")
    @ResponseBody
    public String uploadFile(MultipartFile uploadFile){
        PictureResult pictureResult = pictureService.uploadPic(uploadFile);
        //需要将Java对象手动装换成json数据
        String json = JsonUtils.objectToJson(pictureResult);
        return json;
    }

//    public PictureResult uploadFile(MultipartFile uploadFile){
//        PictureResult pictureResult = pictureService.uploadPic(uploadFile);
//        return pictureResult;
//    }
}
