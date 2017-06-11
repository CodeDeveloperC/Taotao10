package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.*;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 2017/6/6.
 * 商品查询service
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private TbItemDescMapper itemDescMapper;
    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Override
    public TbItem getItemById(Long itemId) {
        //第一种方法
        //TbItem item = itemMapper.selectByPrimaryKey(itemId);

        //第二种方法
        TbItemExample example = new TbItemExample();
        //创建查询条件
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> list = itemMapper.selectByExample(example);
        TbItem tbItem = null;
        //判断list是否为空
        if (list != null && list.size() > 0) {
            tbItem = list.get(0);
        }

        return tbItem;
    }

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
        //分页处理
        PageHelper.startPage(page, rows);
        //执行查询
        TbItemExample example = new TbItemExample();
        List<TbItem> list = itemMapper.selectByExample(example);
        //取total
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();

        //创建返回值对象
        EasyUIDataGridResult result = new EasyUIDataGridResult(total, list);

        return result;
    }

    @Override
    public TaotaoResult createItem(TbItem item, String desc, String itemParam) {
        //生成商品ID
        long itemId = IDUtils.genItemId();
        //补全TBItem属性
        item.setId(itemId);
        // '商品状态，1-正常，2-下架，3-删除'
        item.setStatus((byte) 1);
        //创建时间和更新时间
        Date date = new Date();
        item.setCreated(date);
        item.setUpdated(date);
        //插入商品表
        itemMapper.insert(item);
        //商品描述
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);
        //插入商品描述数据
        itemDescMapper.insert(tbItemDesc);
        //添加商品规格参数处理
        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(itemParam);
        itemParamItem.setCreated(date);
        itemParamItem.setUpdated(date);
        //插入数据
        itemParamItemMapper.insert(itemParamItem);

        return TaotaoResult.ok();
    }

    @Override
    public String getItemParamHtml(Long itemId) {
        //根据商品ID查询规格参数
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        //执行查询
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
        if (list == null || list.isEmpty()) {
            return "";
        }
        //取规格参数
        TbItemParamItem itemParamItem = list.get(0);
        //取json数据
        String paramData = itemParamItem.getParamData();
        //转换成Java对象
        List<Map> mapList = JsonUtils.jsonToList(paramData, Map.class);
        //遍历list生成html
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("<div class=\"Ptable\">\n");
        stringBuffer.append("<div class=\"Ptable-item\">\n");

        for (Map map :
                mapList) {
            stringBuffer.append("<h3>" + map.get("group") + "</h3>\n");
            stringBuffer.append("<dl>\n");

            List<Map> mapList2 = (List<Map>) map.get("params");
            for (Map map2 : mapList2) {
                stringBuffer.append("<dt>" + map2.get("k") + "</dt><dd>" + map2.get("v") + "</dd>\n");
            }
            stringBuffer.append("</dl>\n");

        }


        stringBuffer.append("</div>\n");
        stringBuffer.append("</div>");

        return stringBuffer.toString();


    }
}













