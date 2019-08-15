package com.example.controller;

import com.example.config.ProjectConf;
import com.example.entity.common.VisitConsequenceParent;
import com.example.entity.common.VisitConsequenceParentImpl;
import com.util.LoadUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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
    /**
     * 上传文件，保存在临时文件夹
     * @param file 上传文件
     * @return fileUrl 文件访问路径 fileSite文件存储路径
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("/uploadFileTemporary")
    public VisitConsequenceParent uploadFileByFrom(@RequestParam("file") MultipartFile file) throws IOException {
        //logger.info("upload file by form");
        VisitConsequenceParentImpl vcp = new VisitConsequenceParentImpl();
        String fileSite =LoadUtils.uploadFileTemporary(file);//写入文件，返回所在地址
        Map map=new HashMap();
        map.put("fileSite",fileSite);
        map.put("fileUrl", ProjectConf.PROJECT_STATIC_URL_FULL+ProjectConf.TEMPORARY_PATH+fileSite);

        vcp.setObject(map);
        vcp.setState(0);
        vcp.setMessage("请求成功");
        return vcp;
    }

}
