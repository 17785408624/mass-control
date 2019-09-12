package com.example;

import com.example.config.UploadFileType;
import com.util.MyStringUril;
import com.util.PropertiesUitls;

import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        int i= (int) (Math.random()*3);
        for(int i1=0;i1<1000;i1++){
            System.out.print((int) (Math.random()*3)+"  |");
            if (i1%20==0){
                System.out.println();
            }
        }

    }
}
