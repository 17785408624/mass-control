package com.example;

import com.example.config.UploadFileType;
import com.util.MyStringUril;

import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        String s= "/ab/cde/fgh/ijk/lmn/";

       System.out.println(s.substring(MyStringUril.lastIndexOf(s,'/',3),s.length()));

    }
}
