package com.example.service;

import com.example.entity.common.FileInfoeEntity;
import org.springframework.web.multipart.MultipartFile;

public interface CommonService {
    FileInfoeEntity userUploadFile(MultipartFile file);
}
