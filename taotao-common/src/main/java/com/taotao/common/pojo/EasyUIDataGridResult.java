package com.taotao.common.pojo;

import java.util.List;

/**
 * <p>Title:com.taotao.common.pojo</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/6.
 */
public class EasyUIDataGridResult {
    private long total;
    private List<?> rows;

    public EasyUIDataGridResult(long total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
