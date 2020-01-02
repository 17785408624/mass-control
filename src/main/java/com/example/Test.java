package com.example;

import com.example.config.UploadFileType;
import com.example.entity.user.UserEntity;
import com.util.MyStringUril;
import com.util.PropertiesUitls;
import com.util.PublicUtil;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws IOException {
        UserEntity u1=new UserEntity();
        u1.setUser_state(2);
        //u1.setUser_password("ss");
        u1.setUser_password("ss");
        u1.setUser_id(1);
        UserEntity u2=new UserEntity();
        u2.setUser_id(1);
        ;
          System.out.println(PublicUtil.isEntityEquality(u1,u2));



    }
}
