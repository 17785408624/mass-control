package com.example.config;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

/**
 * 文件上传配置枚举
 */
public enum  UploadFileType {
    USERINFO("/userinfo","jpg,png"),//上传用户信息
    PROJECT_INFO("/projectinfo","jpg,png")//上传用户信息
    ;

    private String file_path;//上传文件路径
    private String supportTypes;//支持的文件类型 以逗号分隔

    private String getSupportTypes() {
        return supportTypes;
    }

    private void setSupportTypes(String supportTypes) {
        this.supportTypes = supportTypes;
    }

    UploadFileType(String file_path) {
        this.file_path = file_path;
    }

    private String getFile_path() {
        return file_path;
    }

    private void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    UploadFileType(String file_path, String supportTypes) {
        this.file_path = file_path;
        this.supportTypes = supportTypes;
    }


    /**
     * 返回文件上传路径
     * @return
     */
    public String getUploadPath(){
        return ProjectConf.UPLOAD_PATH_FULL+this.file_path;

    }
    /**
     * 返回支持的文件格式
     */
    public String[]getsupportType(){
        return this.getSupportTypes().split(",");
    }

    /**
     * 返回文件访问路径
     * @return
     */
    public String getAccessUlr(){//返回文件访问路径
        return ProjectConf.ACCESS_URL+ProjectConf.PROJECT_STATIC_URL+this.file_path;

    }



}
