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
        if(orderRequests==null||orderRequests.length<1){
            return null;
        }
        OrderRequest[] orderRequests1=null;
        //如果排序字段中为空则视为作废的字段
        for (int i=0;i<orderRequests.length;i++) {
            if(orderRequests[i].getOrderName()!=null
                    &&orderRequests[i].getOrderName()!=""
                    &&orderRequests[i].getOrderName()!=" "
                    &&orderRequests[i].getOrderRule()!=null
                    &&orderRequests[i].getOrderRule()!=" "
                    &&orderRequests[i].getOrderRule()!=""){
                if(orderRequests1==null){
                    orderRequests1=new OrderRequest[]{orderRequests[i]};
                }else{
                    orderRequests1=java.util.Arrays.copyOf(orderRequests1,orderRequests1.length+1);//数组扩容
                }
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
