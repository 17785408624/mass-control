package com.example.entity.resultsparam;

import com.example.entity.common.FileInfoeEntity;
import com.example.entity.user.ExpertInfoEntity;

public class ExpertInfoResults {
    private Integer user_id;
    private ExpertInfoEntity expertInfoEntity;//专家信息
    private FileInfoeEntity expert_info_picture_file;//照片文件
    private FileInfoeEntity expert_info_jadjunct_file;//附件文件

    public ExpertInfoEntity getExpertInfoEntity() {
        return expertInfoEntity;
    }

    public void setExpertInfoEntity(ExpertInfoEntity expertInfoEntity) {
        this.expertInfoEntity = expertInfoEntity;
    }

    public FileInfoeEntity getExpert_info_picture_file() {
        return expert_info_picture_file;
    }

    public void setExpert_info_picture_file(FileInfoeEntity expert_info_picture_file) {
        this.expert_info_picture_file = expert_info_picture_file;
    }

    public FileInfoeEntity getExpert_info_jadjunct_file() {
        return expert_info_jadjunct_file;
    }

    public void setExpert_info_jadjunct_file(FileInfoeEntity expert_info_jadjunct_file) {
        this.expert_info_jadjunct_file = expert_info_jadjunct_file;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
