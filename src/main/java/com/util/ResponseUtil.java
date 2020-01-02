package com.util;

import com.example.entity.common.VisitConsequenceParent;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseUtil {
    /**
     * 向前端返回json参数的请求相应
     * @param response
     * @param vcp
     */
    public static void responseJson(HttpServletResponse response, VisitConsequenceParent vcp) throws IOException {
        PrintWriter out = response.getWriter();//获取响应浏览器打印输出流
        JSONObject jSONObject=JSONObject.fromObject(vcp);//将返回结果转换为json
        out.print(jSONObject);//输出响应数据
        out.flush();
        out.close();

    }
}
