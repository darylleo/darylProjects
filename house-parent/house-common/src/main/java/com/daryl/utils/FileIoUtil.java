package com.daryl.utils;

import com.daryl.customtemplate.MyException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * 文件上传下载
 *
 * @author tz
 * @create 2021-08-26
 */
public class FileIoUtil {
    
    private FileIoUtil() {
        throw new IllegalStateException("Utility class");
    }
    
    /**
     * 文件上传
     *
     * @param file     文件
     * @param path     文件存放路径
     * @param fileName 文件名
     * @throws IOException "file empty"
     */
    public static void fileUpload(MultipartFile file, String path, String fileName) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("file empty");
        }
        String filePath = path + fileName;
        try (InputStream in = file.getInputStream();
             FileOutputStream out = new FileOutputStream(filePath)) {
            int count;
            while ((count = in.read()) != -1) {
                out.write(count);
            }
        }
    }
    
    /**
     * 文件上传
     *
     * @param file 文件
     * @param path 文件存放路径
     * @throws IOException "file empty"
     */
    public static void fileUpload(MultipartFile file, String path) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("file empty");
        }
        String fileName = file.getOriginalFilename();
        fileUpload(file, path, fileName);

    }
    
    /**
     * 文件下载
     *
     * @param fileName 文件名
     * @param filePath 文件存放路径
     * @param response HttpServletResponse
     * @throws IOException "file not exists"
     */
    public static void fileDownload(String fileName, String filePath, HttpServletResponse response) throws IOException {
        
        String path = filePath + fileName;
        
        File file = new File(path);
        if (!file.exists()) {
            throw new IOException("file not exists");
        }
        
        try (OutputStream out = response.getOutputStream();
             FileInputStream in = new FileInputStream(path)) {
            response.reset();
            response.setContentType("application/octet-stream; charset=utf-8");
            response.setCharacterEncoding("utf-8");
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            int count;
            byte[] buffer = new byte[1024];
            while ((count = in.read(buffer)) > 0) {
                out.write(buffer, 0, count);
            }
        }
    }

    /**
     * 将文件名替换成uuid名
     * @param fileName 文件名
     * @return uuid名
     */
    public static String getUUIDName(String fileName){
        if (StringUtils.isBlank(fileName)){
            return fileName;
        }
        int index = fileName.lastIndexOf(".");
        if(index==-1){
            return UUID.randomUUID().toString().replace("-", "");
        }else{
            return UUID.randomUUID().toString().replace("-", "")+fileName.substring(index);
        }
    }


    /**
     * 将File转成multipartFile
     *
     * @param filePath 文件路径
     * @return multipartFile
     */
    public static MultipartFile getMultipartFile(String filePath) {
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        String textFieldName = "textField";
        int num = filePath.lastIndexOf(".");
        String extFile = filePath.substring(num);
        //生成fileItem
        FileItem item = factory.createItem(textFieldName, getContentType(filePath), true, "MyFileName");
        File newFile = new File(filePath);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        try {
            FileInputStream fis = new FileInputStream(newFile);
            OutputStream os = item.getOutputStream();
            while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            //throw new CustomException("文件不存在");
        }
        return new CommonsMultipartFile(item);
    }

    /**
     * img 转 base64
     *
     * @param path 文件路径
     * @return BASE64 字符串
     */
    public static String imgBase64(String path) {
        MultipartFile file = getMultipartFile(path);
        byte[] fileByte;
        try {
            String contentType = file.getContentType();
            fileByte = file.getBytes();
            String base = java.util.Base64.getEncoder().encodeToString(fileByte);
            return "data:" + contentType + ";base64," + base;
        } catch (IOException e) {
            e.printStackTrace();
            throw MyException.error("文件转base64失败");
        }

    }

    /**
     * 获取文件的ContentType（方式二）
     * 该方式支持本地文件，也支持http/https远程文件
     *
     * @param fileUrl 文件路径
     */
    public static String getContentType(String fileUrl) {
        String contentType = null;
        try {
            contentType = new MimetypesFileTypeMap().getContentType(new File(fileUrl));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentType;
    }
}
