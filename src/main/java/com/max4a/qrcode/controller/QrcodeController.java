package com.max4a.qrcode.controller;

import com.max4a.common.RestMsg;
import com.max4a.common.QrCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;







@Controller
@RequestMapping("/api/")
public class QrcodeController {








    @RequestMapping(value = "/qrcode", method = RequestMethod.GET)
    @ResponseBody
    public static void main(String[] args) throws IOException, NotFoundException {

        MultiFormatReader formatReader = new MultiFormatReader();

        File file = new File("C:\\Users\\chem\\Desktop\\qrcode-java-zxing\\zhao1.png");
        BufferedImage image = ImageIO.read(file);

        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));

        Map hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        Result result = formatReader.decode(binaryBitmap, hints);

        System.out.println("解析结果:" + result.toString());
        System.out.println("二维码格式:" + result.getBarcodeFormat());
        System.out.println("二维码文本内容:" + result.getText());

    }
    public RestMsg<Object> addAsset(HttpServletRequest request) {

        RestMsg<Object> rm = new RestMsg<Object>();
//        String filePath = "C:/Users/chem/Desktop/qrcode/zhao.png";
//        String filePath1 = "C:/Users/chem/Desktop/qrcode/zhao1.png";
//        QrCodeUtils.createQRcode(filePath);
//        QrCodeUtils.getQRresult(filePath1);
        // 获取页面参数
        String name = request.getParameter("name");
        rm.successMsg(name);
        rm.setCode(1);
        return rm;
    }
}


