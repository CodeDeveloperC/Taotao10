package com.taotao.portal.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.pojo.PortalItem;
import com.taotao.portal.service.ItemService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>Title:com.taotao.portal.service.impl</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/12.
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Value("${REST_iTEM_PARAM_URL}")
    private String REST_iTEM_PARAM_URL;
    @Value("${REST_iTEM_DESC_URL}")
    private String REST_iTEM_DESC_URL;
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${REST_iTEM_BASE_URL}")
    private String REST_iTEM_BASE_URL;

    @Override
    public TbItem getItemById(Long itemId) {
        String json = HttpClientUtil.doGet(REST_BASE_URL + REST_iTEM_BASE_URL + itemId);
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, PortalItem.class);
        TbItem data = (TbItem) taotaoResult.getData();
        return data;
    }

    @Override
    public String getItemDescById(Long itemId) {
        String json = HttpClientUtil.doGet(REST_BASE_URL + REST_iTEM_DESC_URL + itemId);
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItemDesc.class);
        TbItemDesc tbItemDesc = (TbItemDesc) taotaoResult.getData();
        String itemDesc = tbItemDesc.getItemDesc();
        return itemDesc;
    }

    @Override
    public String getItemParamById(Long itemId) {
        String json = HttpClientUtil.doGet(REST_BASE_URL + REST_iTEM_PARAM_URL + itemId);
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItemParamItem.class);
        TbItemParamItem tbItemParamItem = (TbItemParamItem) taotaoResult.getData();
        String paramData = tbItemParamItem.getParamData();
        //转换成Java对象
        List<Map> mapList = JsonUtils.jsonToList(paramData, Map.class);
        //遍历list生成html
//        StringBuffer stringBuffer = new StringBuffer();
//
//        stringBuffer.append("<div class=\"Ptable\">\n");
//        stringBuffer.append("<div class=\"Ptable-item\">\n");
//
//        for (Map map :
//                mapList) {
//            stringBuffer.append("<h3>" + map.get("group") + "</h3>\n");
//            stringBuffer.append("<dl>\n");
//
//            List<Map> mapList2 = (List<Map>) map.get("params");
//            for (Map map2 : mapList2) {
//                stringBuffer.append("<dt>" + map2.get("k") + "</dt><dd>" + map2.get("v") + "</dd>\n");
//            }
//            stringBuffer.append("</dl> \n");
//
//        }
//
//
//        stringBuffer.append("</div>\n");
//        stringBuffer.append("</div>");
//
//        return stringBuffer.toString();

        StringBuffer sb = new StringBuffer();

        sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
        sb.append("	<tbody>\n");
        for (Map map : mapList) {
            sb.append("		<tr>\n");
            sb.append("			<th class=\"tdTitle\" colspan=\"2\">" + map.get("group") + "</th>\n");
            sb.append("		</tr>\n");
            // 取规格项
            List<Map> mapList2 = (List<Map>) map.get("params");
            for (Map map2 : mapList2) {
                sb.append("		<tr>\n");
                sb.append("			<td class=\"tdTitle\">" + map2.get("k") + "</td>\n");
                sb.append("			<td>" + map2.get("v") + "</td>\n");
                sb.append("		</tr>\n");
            }
        }
        sb.append("	</tbody>\n");
        sb.append("</table>");

        return sb.toString();

    }
}
