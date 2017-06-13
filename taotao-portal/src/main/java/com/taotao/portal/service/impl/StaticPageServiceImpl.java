package com.taotao.portal.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.portal.service.ItemService;
import com.taotao.portal.service.StaticPageService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;

/**
 * <p>Title:com.taotao.portal.service.impl</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/13.
 */
@Service
public class StaticPageServiceImpl implements StaticPageService {
    @Autowired
    private ItemService itemService;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Value("${STATIC_PAGE_PATH}")
    private String STATIC_PAGE_PATH;
    @Override
    public TaotaoResult getItemHtml(Long itemId) throws Exception{
        TbItem itemById = itemService.getItemById(itemId);
        String itemDescById = itemService.getItemDescById(itemId);
        String itemParamById = itemService.getItemParamById(itemId);

        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        Template template = configuration.getTemplate("item.ftl");

        HashMap<Object, Object> root = new HashMap<>();

        root.put("item", itemById);
        root.put("itemDesc", itemDescById);
        root.put("itemParam", itemParamById);

        FileWriter out = new FileWriter(new File(STATIC_PAGE_PATH + itemId + ".html"));
        template.process(root, out);
        out.flush();
        out.close();
        return TaotaoResult.ok();
    }
}
