package com.me.courses_selection.utils;

import io.netty.handler.codec.base64.Base64Encoder;
import org.apache.commons.codec.digest.DigestUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * md5加密
 */
public class MD5Util {

    /**
     * salt
     * 第一次加密密钥
     */
    private static String a="3J0A1N21";

    /**
     * salt
     * 第二次加密密钥
     */

    private static final String salt="J1u0n6e2";
    /**
     * md5加密函数
     */
    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }

    /**
     * 第一次加密
     */
    public static String FirstCode(String Pass){
        String str= "" + a.charAt(1) + a.charAt(0) + Pass + a.charAt(0) + a.charAt(6) + a.charAt(2);
        return md5(str);
    }

    /**
     * 第二次加密
     */
    public static String SecondCode(String FirPass,String salt){
        String str="" + salt.charAt(7)+salt.charAt(0)+FirPass+salt.charAt(1)+salt.charAt(2)+salt.charAt(1);
        return md5(str);
    }

    /**
     * 两次加密
     */
    public static String FSCode(String Pass,String salt) {
        String FirPass=FirstCode(Pass);
        String SecPass=SecondCode(FirPass,salt);
        return SecPass;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(FirstCode("123456"));
        //b2626fc12283e95a19dc69d93aeb90e1     852332191ad12dae58c9ca1ab5bbd457
        //bf8b32ebe0c0a56b8e25fe14f555c19e
        System.out.println(SecondCode("bf8b32ebe0c0a56b8e25fe14f555c19e",salt));
        System.out.println(FSCode("123456",salt));
        String str=new BASE64Encoder().encode("123456".getBytes("utf-8"));
        System.out.println(str);
        try{
            byte[] b=new BASE64Decoder().decodeBuffer(str);
            str=new String(b,"utf-8");
            System.out.println(str);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
