package com.taotao.portal.pojo;

import java.util.List;

/**
 * <p>Title:com.taotao.search.pojo</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/11.
 */
public class SearchResult {
    /**
     * 包含四个属性：
     1、商品列表
     2、查询结果总记录数
     3、查询结果的总页数
     4、当前页码
     */
    private List<SearchItem> itemList;
    private Long recordCount;
    private int pageCount;
    private int curPage;

    public List<SearchItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<SearchItem> itemList) {
        this.itemList = itemList;
    }

    public Long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Long recordCount) {
        this.recordCount = recordCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }
}
