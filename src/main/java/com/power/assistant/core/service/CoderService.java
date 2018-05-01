package com.power.assistant.core.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author wuhanhong
 * @date 2018 - 04 - 29
 */
@Service
public class CoderService {

    @Value("${web.qr-code-path}")
    private String uploadPath;
    @Value("${config.ueditor.serverPath}")
    private String serverPath;

    public String encode(String contents) {

        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        String imageName = UUID.randomUUID().toString().replace("-", "") + ".png";
        OutputStream out = null;
        try {
            out = new FileOutputStream(uploadPath + imageName);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        BitMatrix bitMatrix;
        try {
            // 生成二维码
            bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, 300, 300, hints);
            MatrixToImageWriter.writeToStream(bitMatrix, "png", out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serverPath + imageName;
    }
}
