package com.max4a.common;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;


public class Base64Convert {
	/**
	 * 文件转换成base64编码字符串
	 * @param fileName
	 * @return
	 */
    public static String fileToBase64(String fileName){
        String strBase64 = null;
        try {
            InputStream in = new FileInputStream(fileName);
            // in.available()返回文件的字节长度
            byte[] bytes = new byte[in.available()];
            // 将文件中的内容读入到数组中
            in.read(bytes);
            strBase64 = new BASE64Encoder().encode(bytes).replaceAll("\r", "").replaceAll("\n", "");//将字节流数组转换为字符串
            in.close();
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return strBase64;
    }
    /**
     * 转换Base64字符串为文件 
     * @param strBase64
     * @param fileName
     */
    public static void base64ToFile(String strBase64,String fileName){
    	byte[] bytes = null;
		try {
			bytes = new BASE64Decoder().decodeBuffer(strBase64);
		} catch (IOException e) {
			e.printStackTrace();
		}
     	ByteArrayInputStream in = new ByteArrayInputStream(bytes);
     	FileOutputStream out = null;
		try {
			File file = new File(fileName);
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			out = new FileOutputStream(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
     	byte[] buffer = new byte[1024 * 1024];
 		int bytesum = 0;
 		int byteread = 0;
 		try {
			while ((byteread = in.read(buffer)) != -1) {
				bytesum += byteread;
				out.write(buffer, 0, bytesum);
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
 		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
 		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }

}
