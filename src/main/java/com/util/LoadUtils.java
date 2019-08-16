package com.util;

import com.example.config.ProjectConf;
import com.example.config.UploadFileType;
import com.example.entity.common.FileInfoeEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.util.UUID;

/**
 * 文件上传、下载读写工具类
 */

public class LoadUtils {
    private static String[] image={
            "png","jpg"
    };
    private static String[] documents={
            "word","xlx","xlsx"
    };

    /**
     * 文件上传写入类，存在当月文件夹，如果当月文件夹不存在会新建
     * @param file 上传的文件
     * @param uploadFileType 上传的文件属于哪个业务
     * @return 文件名
     */
    public static FileInfoeEntity uploadFile(MultipartFile file, UploadFileType uploadFileType){
       //获得原来文件名(含后缀名)
        String originalFilename = file.getOriginalFilename();
        int pos = originalFilename.lastIndexOf(".");
        //原文件后缀名
        String suffix = originalFilename.substring(pos);
        //保存文件
       //ServletContext application = session.getServletContext();
       //String realPath = application.getRealPath("D:\\workspace111\\ycpolice-web\\web\\static\\updownload");

        String uuid = UUID.randomUUID().toString(); //产生一个uuid随机文件名

        String dir=uploadFileType.getUploadPath()+getMarkDirPath();//上传文件所在文件夹路径
        String fileName=uuid + suffix;
        String fullPath = dir+ "/"+fileName;//文件完整路径
        File dirFile=new File(dir);
        if (!dirFile.exists()) {//如果文件夹不存在新创建一个
            dirFile.mkdirs();// mkdirs创建多级目录
        }
        InputStream in= null;
        OutputStream out=null;
        try {
            out= new FileOutputStream(new File(fullPath));//输出流
            in = file.getInputStream();//输入流
            int len = 0;
            byte[] buf = new byte[3 * 1024];
            while ((len = in.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        FileInfoeEntity fileInfoeEntity=new FileInfoeEntity();
        fileInfoeEntity.setFile_name(originalFilename);//文件名
        fileInfoeEntity.setFile_info_path(fullPath);//文件路径
        fileInfoeEntity.setFile_url(uploadFileType.getAccessUlr()+getMarkDirPath()+"/"+fileName);//文件访问路径
        fileInfoeEntity.setFile_type(fileTypeJudge(suffix));//文件类型

        return fileInfoeEntity;//返回文件夹名以及文件名
    };

    /**
     * 上传临时文件
     * @param file
     * @return
     */
    public static String uploadFileTemporary(MultipartFile file){
        //获得原来文件名(含后缀名)
        String originalFilename = file.getOriginalFilename();
        int pos = originalFilename.lastIndexOf(".");
        //原文件后缀名
        String suffix = originalFilename.substring(pos);
        //保存文件
        //ServletContext application = session.getServletContext();
        //String realPath = application.getRealPath("D:\\workspace111\\ycpolice-web\\web\\static\\updownload");

        String uuid = UUID.randomUUID().toString(); //产生一个uuid随机文件名

        String dir= ProjectConf.TEMPORARY_PATH_FULL+getMarkDirPath();//上传临时文件所在文件夹路径
        String fullPath = dir+uuid + suffix;//文件完整路径
        File dirFile=new File(dir);
        if (!dirFile.exists()) {//如果文件夹不存在新创建一个
            dirFile.mkdirs();// mkdirs创建多级目录
        }
        InputStream in= null;
        OutputStream out=null;
        try {
            out= new FileOutputStream(new File(fullPath));//输出流
            in = file.getInputStream();//输入流
            int len = 0;
            byte[] buf = new byte[3 * 1024];
            while ((len = in.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return getMarkDirPath()+uuid + suffix;//返回文件夹名以及文件名
    };


    private static String getMarkDirPath(){//获取文件夹后部分 动态生成的文件夹路径
        LocalDate today = LocalDate.now();//当前时间
        return "/"+today.getYear()+"-"+today.getMonthValue()+"-"+today.getDayOfMonth();
    }
    public static int fileTypeJudge(String suffix){

        return 1;
    }

}
