package com.taotao.rest.service.impl;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.ItemCatResult;
import com.taotao.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title:com.taotao.rest.service.impl</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/9.
 */
@Controller
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;


    @Override
    public ItemCatResult getItemCatList() {
        //调用递归方法查询商品分类列表
        List catList = getItemCatList((long) 01);
        //返回结果
        ItemCatResult itemCatResult = new ItemCatResult();
        itemCatResult.setData(catList);
        return itemCatResult;
    }

    private List getItemCatList(Long parentId) {
        //根据parentID查询列表
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        ArrayList<Object> resultList = new ArrayList<>();
        int index = 0;//设置取14条记录
        for (TbItemCat tbItemCat : list) {
            if (index >= 14) {
                break;
            }
            //如果是父节点
            if (tbItemCat.getIsParent()) {
                CatNode node = new CatNode();
                node.setUrl("products/" + tbItemCat.getId() + ".html");
                //如果当前结点为第一级结点
                if (tbItemCat.getParentId() == 0) {
                    node.setName("<a href='/products/" + tbItemCat.getId() + ".html'>" + tbItemCat.getName() + "</a>");
                    index++;//第一级结点不能超过14个

                } else {
                    node.setName(tbItemCat.getName());
                }

                node.setItems(getItemCatList(tbItemCat.getId()));
                //将node添加到列表中
                resultList.add(node);
            } else {
                //如果是叶子结点
                String item = "/products/" + tbItemCat.getId() + ".html|" + tbItemCat.getName();
                resultList.add(item);

            }
        }
        return resultList;
    }
}
