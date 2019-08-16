package com.example;

import com.example.config.UploadFileType;
import com.util.MyStringUril;
import com.util.PropertiesUitls;

import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
      System.out.println(
              PropertiesUitls.getCommonYml("ACCESS_URL"));

    }
}
