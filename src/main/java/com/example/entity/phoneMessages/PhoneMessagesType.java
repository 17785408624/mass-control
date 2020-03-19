package com.example.entity.phoneMessages;

/**
 * 手机短信类型
 */
public enum PhoneMessagesType {
     messagesBoundCod("boundCod","绑定手机验证码",3),
    reminderMessages("reminderMessages","发送提醒消息",3);
     private String messagesId;//消息类型id
     private String messagesName;//消息类型名字
     private Integer sndMessagesRestrict;//发送次数限制
    PhoneMessagesType(String messagesId, String messagesName,Integer sndMessagesRestrict) {
        this.messagesId=messagesId;
        this.messagesName=messagesName;
        this.sndMessagesRestrict=sndMessagesRestrict;

    }

    public String getMessagesId() {
        return messagesId;
    }

    public void setMessagesId(String messagesid) {
        this.messagesId = messagesid;
    }

    public String getMessagesName() {
        return messagesName;
    }

    public void setMessagesName(String messagesName) {
        this.messagesName = messagesName;
    }

    public Integer getSndMessagesRestrict() {
        return sndMessagesRestrict;
    }

    public void setSndMessagesRestrict(Integer sndMessagesRestrict) {
        this.sndMessagesRestrict = sndMessagesRestrict;
    }
}
