package com.taotao.common.pojo;

/**
 * <p>Title:com.taotao.common.pojo</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/8.
 */
public class PictureResult {
    private int error;
    private String url;
    private String message;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
