package com.example.entity.common;

public class FileInfoeEntity {
    private Integer file_info_id;//数据主键
    private Integer file_type;//文件类型 1图片 2文档 3其它
    private String file_name;//文件名
    private String file_info_path;//文件路径
    private String file_url;//文件访问地址


    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }


    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }


    public Integer getFile_info_id() {
        return file_info_id;
    }

    public void setFile_info_id(Integer file_info_id) {
        this.file_info_id = file_info_id;
    }


    public String getFile_info_path() {
        return file_info_path;
    }

    public void setFile_info_path(String file_info_path) {
        this.file_info_path = file_info_path;
    }

    public Integer getFile_type() {
        return file_type;
    }

    public void setFile_type(Integer file_type) {
        this.file_type = file_type;
    }
}
