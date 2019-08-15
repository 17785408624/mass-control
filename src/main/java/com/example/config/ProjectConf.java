package com.example.config;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class ProjectConf {
    public static String ACCESS_PROTOCOL="http://";//访问协议
    public static String WEB_URL="localhost/";//ip地址
    public static String ACCESS_URL=ACCESS_PROTOCOL+WEB_URL;//项目访问地址
    public static String PROJECT_ROOT_PATH;//项目路径
    public static String PROJECT_STATIC_PATH="resources/static/";//静态资源路径
    public static String PROJECT_STATIC_PATH_FULL;//静态资源完整路径
    public static String PROJECT_STATIC_URL="static/";//静态资源访问路径
    public static String PROJECT_STATIC_URL_FULL=ACCESS_URL+PROJECT_STATIC_URL;//静态资源访问完整路径
    public static String UPLOAD_PATH="file/upload/";//文件上传路径
    public static String UPLOAD_PATH_FULL;//文件上传完整路径
    public static String TEMPORARY_PATH="file/upload/temporary/";//临时文件上传路径
    public static String TEMPORARY_PATH_FULL;//临时文件完整路径
    static {
        try {
            PROJECT_ROOT_PATH= new File("").getCanonicalPath().replace("\\","/")+"/";
        } catch (IOException e) {
            e.printStackTrace();
        }
        PROJECT_STATIC_PATH_FULL=PROJECT_ROOT_PATH+"src/main/"+"resources/static/";
        UPLOAD_PATH_FULL=PROJECT_STATIC_PATH_FULL+"file/upload/";
        TEMPORARY_PATH_FULL=UPLOAD_PATH_FULL+"temporary/";


    }

}
