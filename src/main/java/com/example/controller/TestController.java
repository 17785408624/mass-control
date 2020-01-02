package com.example.controller;


import com.example.config.UploadFileType;
import com.example.entity.TestEntity;
import com.example.entity.common.VisitConsequenceParent;
import com.example.entity.common.VisitConsequenceParentImpl;
import com.example.entity.requstparam.PageRequest;
import com.util.LoadUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.io.*;

@Controller
@RequestMapping("test")
public class TestController {
    //jsp页面的视图解析器


    private Logger logger;

    //    @RequestMapping(value = "/uploadFileByFrom")
//    public VisitConsequenceParent uploadFileByFrom(@RequestParam("file") MultipartFile file, TestEntity testEntity) throws IOException {
//        //logger.info("upload file by form");
//
//        VisitConsequenceParentImpl vcp = new VisitConsequenceParentImpl();
//        if (!file.isEmpty()) {
//            vcp.setObject(LoadUtils.uploadFile(file,UploadFileType.USERINFO));
//        } else {
//            // result.errorResult(MsgConstantUtil.REQUEST_PARAMETER_ERROR);
//        }
//        return vcp;
//    }
    @RequestMapping("/index")
    public String home() {
        return "index";
    }

}
