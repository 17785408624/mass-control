package com.example;

import com.example.config.UploadFileType;
import com.util.MyStringUril;
import com.util.PropertiesUitls;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws IOException {
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date=null;
        try {
            date= dateFormat1.parse("1970-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date.getTime());
        System.out.println(new Date(0));

    }
}
