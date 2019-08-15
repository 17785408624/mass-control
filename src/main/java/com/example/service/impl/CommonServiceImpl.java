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
    @Override
    public FileInfoeEntity userUploadFile(MultipartFile file) {
        FileInfoeEntity fileInfoeEntity=LoadUtils.uploadFile(file, UploadFileType.USERINFO);
        fileMapper.insertFileInfo(fileInfoeEntity);

        return fileInfoeEntity;
    }
}
