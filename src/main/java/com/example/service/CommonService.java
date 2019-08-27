package com.example.service;

import com.example.entity.common.FileInfoeEntity;
import org.springframework.web.multipart.MultipartFile;

public interface CommonService {
    /**
     * 上传用户信息文件
     * @param file
     * @return
     */
    FileInfoeEntity userUploadFile(MultipartFile file);

    /**
     * 上传项目信息文件
     * @param file
     * @return
     */
    FileInfoeEntity projectUploadFile(MultipartFile file);
}
