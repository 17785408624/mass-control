package com.example.entity.phoneMessages;

/**
 * 手机短信
 */
public class PhoneMessagesEntity {
    private String  phoneMessagesId;
    private PhoneMessagesType phoneMessagesType;//信息类型
    private String phoneNum;//手机号
    private Integer sendNum;//当前类型发送的信息次数
    private String[]sendData;//自定义的发送信息
    private String fullContent;//短信完整內容
    private Long sendDate;//发送时间
    public PhoneMessagesEntity() {
    }

    public PhoneMessagesType getPhoneMessagesType() {
        return phoneMessagesType;
    }

    public void setPhoneMessagesType(PhoneMessagesType phoneMessagesType) {
        this.phoneMessagesType = phoneMessagesType;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getSendNum() {
        return sendNum;
    }

    public void setSendNum(Integer sendNum) {
        this.sendNum = sendNum;
    }

    public Long getSendDate() {
        return sendDate;
    }

    public void setSendDate(Long sendDate) {
        this.sendDate = sendDate;
    }

    public String getPhoneMessagesId() {
        return phoneMessagesId;
    }

    public void setPhoneMessagesId(String phoneMessagesId) {
        phoneMessagesId = phoneMessagesId;
    }

    public String[] getSendData() {
        return sendData;
    }

    public void setSendData(String[] sendData) {
        this.sendData = sendData;
    }

    public String getFullContent() {
        return fullContent;
    }

    public void setFullContent(String fullContent) {
        this.fullContent = fullContent;
    }

}
