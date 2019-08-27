package com.example.service;

import com.example.entity.common.PageResult;

import com.example.entity.requstparam.PageRequest;
import com.example.mapper.TestMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestMapper testMapper;

    @Override
    public Map findByid(int id) {
        return null;
    }

    @Override
    public List<Map> findAll() {
        return testMapper.selectAll();
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        /*
         * 调用分页插件完成分页
         * @param
         * @return
         */
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);//调用分页
        List<Map> sysMenus = testMapper.selectPage();
        PageInfo a=new PageInfo<Map>(sysMenus);
        return PageUtils.getPageResult(pageRequest, a);
    }


}
