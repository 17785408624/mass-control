package com.example.entity.requstparam;

import com.util.EncryptUtil;

import java.util.List;

public class PageOderRequest {
    OrderRequest[] orderRequests;

    PageRequest pageRequest;

    /**
     * 获取排序字段
     * @return
     */
    public com.example.entity.requstparam.OrderRequest[] getOrderRequests() {
        OrderRequest[] orderRequests1=new OrderRequest[0];
        if(orderRequests==null||orderRequests.length<1){
            return null;
        }
        for (int i=0;i<orderRequests.length;i++) {
            if(orderRequests[i].getOrderName()!=null
                    &&orderRequests[i].getOrderName()!=""
                    &&orderRequests[i].getOrderRule()!=null
                    &&orderRequests[i].getOrderRule()!=""){
                orderRequests1=java.util.Arrays.copyOf(orderRequests1,orderRequests1.length+1);
                orderRequests1[i]=orderRequests[i];
            }
        }
        return EncryptUtil.decodeOrderField(orderRequests1);//解密排序字段
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
