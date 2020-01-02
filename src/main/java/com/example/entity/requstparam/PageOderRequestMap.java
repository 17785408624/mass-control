package com.example.entity.requstparam;

import java.util.Map;

/**
 * 封装分页请求的数据。带map参数
 */
public class PageOderRequestMap extends PageOderRequest{
    private Map param;//前端传入非分页和排序的请求参数

    public Map getParam() {
        return param;
    }

    public void setParam(Map param) {
        this.param = param;
    }
}
