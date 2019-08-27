package com.example.controller;


import com.example.config.UploadFileType;
import com.example.entity.TestEntity;
import com.example.entity.common.VisitConsequenceParent;
import com.example.entity.common.VisitConsequenceParentImpl;
import com.example.entity.requstparam.PageRequest;
import com.example.service.TestService;
import com.util.LoadUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("test")
public class TestController {
    private Logger logger;
    @Autowired
    private TestService testService;

    @GetMapping(value = "/findAll")
    public Object findAll() {
        return testService.findAll();
    }

    @PostMapping(value = "/findPage")
    public Object findPage(@RequestBody PageRequest pageQuery) {
        return testService.findPage(pageQuery);
    }


    @ResponseBody
    @RequestMapping(value = "/uploadFileByFrom")
    public VisitConsequenceParent uploadFileByFrom(@RequestParam("file") MultipartFile file, TestEntity testEntity) throws IOException {
        //logger.info("upload file by form");

        VisitConsequenceParentImpl vcp = new VisitConsequenceParentImpl();
        if (!file.isEmpty()) {
            vcp.setObject(LoadUtils.uploadFile(file,UploadFileType.USERINFO));
        } else {
            // result.errorResult(MsgConstantUtil.REQUEST_PARAMETER_ERROR);
        }
        return vcp;
    }


}
