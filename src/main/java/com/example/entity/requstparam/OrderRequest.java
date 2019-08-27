package com.example.entity.requstparam;

public class OrderRequest {
    public String orderName;//排序属性名
    public String orderRule;//排序规则// ASC升序 DESC降序

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderRule() {
        return orderRule;
    }

    public void setOrderRule(String orderRule) {
        this.orderRule = orderRule;
    }
}
