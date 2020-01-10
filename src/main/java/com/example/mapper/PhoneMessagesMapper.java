package com.example.mapper;


import com.example.entity.phoneMessages.PhoneMessagesEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneMessagesMapper {
     int insertPhoneMessages(PhoneMessagesEntity phoneMessagesEntity);
}
