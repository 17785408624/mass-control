package com.example.service.impl;

import com.example.service.AdminService;

public class AdminServiceImpl implements AdminService {
    //重置密码为默认值
    @Override
    public Boolean resetPassword(String userCod) {
        String password="123";
        return null;
    }
    //重置密码
    @Override
    public Boolean resetPassword(String userCod,String password) {
        return null;
    }
}
