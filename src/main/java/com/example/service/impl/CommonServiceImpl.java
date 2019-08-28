package com.example.service.impl;

import com.example.config.UploadFileType;
import com.example.entity.common.FileInfoeEntity;
import com.example.mapper.FileMapper;
import com.example.service.CommonService;
import com.util.LoadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
@Transactional(rollbackFor=Exception.class)
@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    FileMapper fileMapper;
    //上传用户信息文件
    @Override
    public FileInfoeEntity userUploadFile(MultipartFile file) {
        FileInfoeEntity fileInfoeEntity=LoadUtils.uploadFile(file, UploadFileType.USERINFO);
        fileMapper.insertFileInfo(fileInfoeEntity);

        return fileInfoeEntity;
    }
    //上传项目信息文件
    @Override
    public FileInfoeEntity projectUploadFile(MultipartFile file) {
        FileInfoeEntity fileInfoeEntity=LoadUtils.uploadFile(file, UploadFileType.PROJECT_INFO);
        fileMapper.insertFileInfo(fileInfoeEntity);

        return fileInfoeEntity;
    }

    @Override
    public FileInfoeEntity findFileInfoAllById(Integer file_info_id) {
        return fileMapper.selectFileInfoAllById(file_info_id);
    }
}
