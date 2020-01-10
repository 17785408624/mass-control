package com.example.api;

import com.example.common.exceptiondefine.SedMessagesException;
import com.example.entity.phoneMessages.PhoneMessagesEntity;

import java.util.Map;

/**
 * 手机通讯/交互
 */
public interface PhoneMessages {
    public Map sedMessages(PhoneMessagesEntity pme) throws SedMessagesException;//发送短信
}
