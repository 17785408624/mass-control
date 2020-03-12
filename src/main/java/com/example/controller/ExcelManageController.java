package com.example.controller;

import com.example.service.ExcelManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
@Controller
@RequestMapping("ExcelManage")
public class ExcelManageController {
    @Autowired
    private ExcelManage excelManage;

    //获取图片验证码
    @ResponseBody
    @RequestMapping("/downloadExcel")
    public void downloadExcel(HttpServletResponse response, @RequestBody Map param) {
        Integer typeCode=Integer.valueOf(param.get("typeCode").toString());
        Integer index=Integer.valueOf(param.get("index").toString());
        Integer num=Integer.valueOf(param.get("num").toString());
        OutputStream os=null;
        try {
            os=response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //excelManage.outputExcel(os,1,1,20);

    }

}
