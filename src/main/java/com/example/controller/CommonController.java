package com.example.controller;

import com.example.config.ProjectConf;
import com.example.entity.common.FileInfoeEntity;
import com.example.entity.common.VisitConsequenceParent;
import com.example.entity.common.VisitConsequenceParentImpl;
import com.example.service.CommonService;
import com.example.service.TestService;
import com.util.LoadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@RestController
@RequestMapping("/common")
public class CommonController {
    @Autowired
    private CommonService commonService;
    /**
     * 上传用户信息文件
     * @param file 上传文件
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("/uploadFileTemporary")
    public VisitConsequenceParent uploadFileUser(@RequestParam("file") MultipartFile file) throws IOException {
        //logger.info("upload file by form");
        VisitConsequenceParentImpl vcp = new VisitConsequenceParentImpl();
        FileInfoeEntity fileInfoeEntity=commonService.userUploadFile(file);
        vcp.setObject(fileInfoeEntity);
        vcp.setState(0);
        vcp.setMessage("请求成功");
        return vcp;
    }
    @ResponseBody
    @RequestMapping("/uploadFileProject")
    public VisitConsequenceParent uploadFileProject(@RequestParam("file") MultipartFile file) throws IOException {
        //logger.info("upload file by form");
        VisitConsequenceParentImpl vcp = new VisitConsequenceParentImpl();
        FileInfoeEntity fileInfoeEntity=commonService.projectUploadFile(file);
        vcp.setObject(fileInfoeEntity);
        vcp.setState(0);
        vcp.setMessage("请求成功");
        return vcp;
    }

    /**
     * 通过文件id查询文件全部信息
     */
    @ResponseBody
    @PostMapping("/findFileInfoAll")
    public VisitConsequenceParent findFileInfoAll(
            @RequestBody Map param) throws IOException {
        //logger.info("upload file by form");
        Integer file_info_id=Integer.
                parseInt(String.valueOf(param.get("file_info_id")));//文件id
        VisitConsequenceParentImpl vcp = new VisitConsequenceParentImpl();
        FileInfoeEntity fileInfoeEntity=
                commonService.findFileInfoAllById(file_info_id);//查询文件全部信息
        vcp.setObject(fileInfoeEntity);
        vcp.setState(0);
        vcp.setMessage("请求成功");
        return vcp;
    }
}
