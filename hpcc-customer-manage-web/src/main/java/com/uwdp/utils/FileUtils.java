package com.uwdp.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author lx
 * @data 2023/6/27 16:35
 */
@Slf4j
@Component
public class FileUtils {

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static final int AVATAR_MAX_SIZE = 1 * 1024 * 1024;

    /**
     * 上传文件
     * @param uploadFile
     * @param uploadUrl
     * @param size
     * @return
     * @throws IOException
     */
    public static String upload(MultipartFile uploadFile,String uploadUrl,Integer size) throws IOException {
        checkFile(uploadFile,size);
        String format = sdf.format(new Date());
        log.info("文件开始上传："+uploadFile.getName());
        File folder = new File(uploadUrl + format);
        log.info("文件上传路径: " ,uploadUrl);
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        String oldName = uploadFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString() +
                oldName.substring(oldName.lastIndexOf("."), oldName.length());

        uploadFile.transferTo(new File(folder, newName));
        String url = uploadUrl +format +"/"+ newName;
        log.info("文件上传成功："+url);
        return url;
    }


    /**
     * 检查文件
     * @param uploadFile
     * @param size
     * @throws IOException
     */
    private static void checkFile(MultipartFile uploadFile,Integer size) throws IOException{
        if (uploadFile.isEmpty()) {
            throw new IOException("上传的文件不允许为空");
        }

        int maxSize = AVATAR_MAX_SIZE * size;
        // 判断上传的文件大小是否超出限制值
        if (uploadFile.getSize() > maxSize) {
            // 是：抛出异常
            throw new IOException("不允许上传超过" + (maxSize) + "KB的文件");
        }
    }
}
