package com.power.assistant.base;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author wuhanhong
 * @date 2018 - 04 - 30
 */
public class FileUtils {

    public static String[] upload(MultipartFile upfile, String serverPath, String uploadPath){
        String result[] = new String[3];
        String fileName = upfile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID().toString().replaceAll("-", "") + suffix;
        File dest = new File(uploadPath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            upfile.transferTo(dest);
            result[0] = serverPath + fileName;
            result[1] = fileName;
            result[2] = fileName;
        }catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
