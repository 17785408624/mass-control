package com.example.service;

import com.example.entity.common.PageResult;
import com.example.entity.requstparam.PageRequest;

import java.util.List;
import java.util.Map;

public interface TestService {
    Map findByid(int id);
    List<Map> findAll();
    PageResult findPage(PageRequest pageRequest);
}
