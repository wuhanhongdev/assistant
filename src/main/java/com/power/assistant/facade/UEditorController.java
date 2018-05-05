package com.power.assistant.facade;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.power.assistant.base.FileUtils;
import com.power.assistant.base.PublicMsg;
import com.power.assistant.model.Ueditor;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.ueditor.MyActionEnter;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UEditorController {

    @Value("${config.ueditor.serverPath.ueditor}")
    private String serverPath;
    @Value("${web.upload-path}")
    private String uploadPath;

    @RequestMapping("/ueditor/imgUpload")
    public @ResponseBody Ueditor imgUpLoad(MultipartFile upfile) {
        String url = "";
        if (upfile != null) {
            String[] upload = FileUtils.upload(upfile,serverPath,uploadPath);

            return new Ueditor("SUCCESS",upload[0],upload[1],upload[2]);
        }

        return new Ueditor("FAIL","","","");
    }

    @RequestMapping("/ueditor/controller")
    @ResponseBody
    public String ueditor(HttpServletRequest request) {
        return PublicMsg.UEDITOR_CONFIG;
    }
}
