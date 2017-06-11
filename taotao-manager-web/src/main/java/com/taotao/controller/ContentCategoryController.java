package com.taotao.controller;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>Title:com.taotao.controller</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/10.
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCatList(@RequestParam(value = "id", defaultValue = "0") Long parentid) {
        List<EasyUITreeNode> contentCatList = contentCategoryService.getContentCatList(parentid);
        return contentCatList;
    }

    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult createNode(Long parentId, String name) {
        TaotaoResult taotaoResult = contentCategoryService.insertCategory(parentId, name);
        return taotaoResult;
    }
}
