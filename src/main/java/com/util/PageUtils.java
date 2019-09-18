package com.util;

import com.example.entity.common.VisitConsequencePage;
import com.example.entity.requstparam.PageRequest;
import com.github.pagehelper.PageInfo;

public class PageUtils {
    /**
     * 将分页信息封装到统一的对象
     * @param pageRequest
     * @param
     * @return
     */
//    public static PageResult getPageResult(PageRequest pageRequest, PageInfo<?> pageInfo) {
//        PageResult pageResult = new PageResult();
//        pageResult.setPageNum(pageInfo.getPageNum());
//        pageResult.setPageSize(pageInfo.getPageSize());
//        pageResult.setTotalSize(pageInfo.getTotal());
//        pageResult.setTotalPages(pageInfo.getPages());
//        pageResult.setContent(pageInfo.getList());
//        return pageResult;
//    }
    public static VisitConsequencePage getVisitConsequencePage( PageInfo<?> pageInfo) {
        VisitConsequencePage pageResult=new VisitConsequencePage();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setObject(pageInfo.getList());
        return pageResult;
    }

}
