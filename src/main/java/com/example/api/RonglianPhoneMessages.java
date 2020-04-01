package com.example.api;

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.example.common.exceptiondefine.SedMessagesException;
import com.example.entity.phoneMessages.PhoneMessagesEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 荣联云通讯服务封装
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class RonglianPhoneMessages implements PhoneMessages{
    /**
     * 发送手机验证码
     * @param pme
     * @throws SedMessagesException 发送手机信息错误异常
     */
    String serverIp = "app.cloopen.com";
    //请求端口
    String serverPort = "8883";
    //主账号,登陆云通讯网站后,可在控制台首页看到开发者主账号ACCOUNT SID和主账号令牌AUTH TOKEN


    /**
     * 发送短信
     * @param pme
     * @return
     * @throws SedMessagesException
     */
    @Override
    public Map sedMessages(PhoneMessagesEntity pme) throws SedMessagesException {
        String templateId ="";//短信模板
        String templateContent="";//模板内容
        String accountSId = "8a216da859925b470159957760e20084";
        String accountToken = "47fd59d5e57b4d4e88a1ca8c09dde150";
        //请使用管理控制台中已创建应用的APPID
        String appId = "8a216da859925b470159957761330089";
        switch (pme.getPhoneMessagesType().getMessagesId()){
            case "boundCod" :templateId="217169";//验证码

                templateContent="【贵州煤矿研究院】您的验证码为"+pme.getSendData()[0]+"，请于"
                        +pme.getSendData()[1]+"内正确输入，如非本人操作，请忽略此短信。";

            break;
        }
        CCPRestSmsSDK sdk = new CCPRestSmsSDK();
        sdk.init(serverIp, serverPort);
        sdk.setAccount(accountSId, accountToken);
        sdk.setAppId(appId);
        sdk.setBodyType(BodyType.Type_JSON);
        HashMap<String, Object> result = sdk.sendTemplateSMS(pme.getPhoneNum(), templateId, pme.getSendData());//发送短信
        result.put("templateContent",templateContent);
        if ("000000".equals(result.get("statusCode"))) {
            return result;
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
