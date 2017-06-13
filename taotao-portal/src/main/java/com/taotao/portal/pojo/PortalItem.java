package com.taotao.portal.pojo;

import com.taotao.pojo.TbItem;

/**
 * <p>Title:com.taotao.portal.pojo</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/12.
 */
public class PortalItem extends TbItem {
    public String[] getImages(){
        String images = this.getImage();
        if (images != null && !images.equals("")) {
            String[] imgs = images.split(",");
            return imgs;
        }

        return null;
    }
}
