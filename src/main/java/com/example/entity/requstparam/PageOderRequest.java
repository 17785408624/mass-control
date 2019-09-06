package com.example.entity.requstparam;

import com.util.EncryptUtil;

import java.util.List;

public class PageOderRequest {
    OrderRequest[] orderRequests;

    PageRequest pageRequest;

    public com.example.entity.requstparam.OrderRequest[] getOrderRequests() {

        return EncryptUtil.decodeOrderField(orderRequests);//解密排序字段
    }

    public void setOrderRequests(com.example.entity.requstparam.OrderRequest[] orderRequests) {
        this.orderRequests = orderRequests;
    }



    public PageRequest getPageRequest() {
        return pageRequest;
    }

    public void setPageRequest(PageRequest pageRequest) {
        this.pageRequest = pageRequest;
    }
}
