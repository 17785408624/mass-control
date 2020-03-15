package com.example.controller;

import com.example.service.ExcelManageService;
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
    private ExcelManageService excelManage;

    //获取图片验证码

    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response, @RequestBody Map param) {
        Integer typeCode=Integer.valueOf(param.get("typeCode").toString());
        excelManage.exportExcel(response,typeCode);
        }

}
