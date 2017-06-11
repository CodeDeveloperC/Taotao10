package com.taotao.pagehelper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * <p>Title:com.taotao.pagehelper</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/6.
 */
public class TestPageHelper {
    private ApplicationContext applicationContext;

    @Before
    public void init() {
        //创建一个spring容器
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
    }

    @Test
    public void testPageHelper() throws Exception {
        //从spring容器中获得mapper的代理对象
        TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);
        //设置分页信息
        PageHelper.startPage(1, 30);
        //执行查询
        List<TbItem> list = itemMapper.selectByExample(new TbItemExample());

        //取查询结果
        PageInfo<Object> pageInfo = new PageInfo<>();
        long total = pageInfo.getTotal();
        System.out.println("total:" + total);
        System.out.println(list.size());
        for (TbItem tbItem : list
                ) {
            System.out.println(tbItem.getTitle());
        }
    }
}
