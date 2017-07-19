package cn.scau.hjr.util;

import org.apache.shiro.codec.Base64;

/**
 * Created by Administrator on 2017/7/19 0019.
 */
public class ShiroUtils
{

    public static String encodeToString(String password)
    {
        return Base64.encodeToString(password.getBytes());
     }
     public static String deCodeToString(String password)
     {
         return Base64.decodeToString(password.getBytes());
     }
}
