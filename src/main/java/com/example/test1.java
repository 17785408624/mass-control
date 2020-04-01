package com.example;

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.example.common.exceptiondefine.SedMessagesException;
import com.example.entity.phoneMessages.PhoneMessagesEntity;
import com.example.entity.phoneMessages.PhoneMessagesType;
import com.util.PublicUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;

public class test1 {
    public static void main(String[]args) throws SedMessagesException {
        String serverIp = "app.cloopen.com";
        //请求端口
        String serverPort = "8883";
        String templateId ="577875";//短信模板
        String templateContent="";//模板内容
        String accountSId = "8a216da859925b470159957760e20084";
        String accountToken = "47fd59d5e57b4d4e88a1ca8c09dde150";
        //请使用管理控制台中已创建应用的APPID
        String appId = "8a216da86f696570016f8e9baa4713ed";
//        String phoneNums="18785886555,13809493083,13985530339,13885843503,17785817583,13885803618,15195804387,18753838666,18888250899,13521783997,18755414267,15599159111" +
//                "13985494666,13885173050,18905162967,15996376176,13955707673,15208588963,18533260707,13885882006,13908588999,13628588099,13985124656," +
//                "13885868084,15922939133,15285580887,13984767499,18286118863,13158072497,17785133391,15085784899,15186381336,18798830455,13653972055," +
//                "18003711509,13939026051,13939026050,13523061581,13838293722,13849187481,13783616577,13598070915,19985753969,17785408624,18722905204";
        String phoneNums="13985729376,13985524151";

        String[] sendData=new String[]{"专家信息","30"};

        CCPRestSmsSDK sdk = new CCPRestSmsSDK();
        sdk.init(serverIp, serverPort);
        sdk.setAccount(accountSId, accountToken);
        sdk.setAppId(appId);
        sdk.setBodyType(BodyType.Type_JSON);
        HashMap<String, Object> result = sdk.sendTemplateSMS(phoneNums, templateId, sendData);//发送短信
        result.put("templateContent",templateContent);
        if ("000000".equals(result.get("statusCode"))) {
            System.out.println("成功");
            //正常返回输出data包体信息（map）
//            HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
//            Set<String> keySet = data.keySet();
//            for (String key : keySet) {
//                Object object = data.get(key);
//                System.out.println(key + " = " + object);
//            }

        } else {
            throw new SedMessagesException("错误码=" + result.get("statusCode") + " 错误信息：" + result.get("statusMsg"));

            //异常返回输出错误码和错误信息
        }


    }
    }

