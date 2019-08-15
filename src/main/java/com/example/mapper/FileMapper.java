package com.example.mapper;

import com.example.entity.common.FileInfoeEntity;

public interface FileMapper {
    /**
     * 添加文件信息
     * @param fileInfo
     * @return
     */
    int insertFileInfo(FileInfoeEntity fileInfo);

    /**
     * 通过数据主键查询文件信息
     * @param file_info_id
     * @return
     */
    FileInfoeEntity selectFileInfoById(int file_info_id);
}
