package cn.scau.hjr.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Created by Administrator on 2017/7/21 0021.
 */
public class shiroUtil {

    //加密函数
    public static String encode(String password,String account)
    {
        return new Md5Hash(password,account).toString();
    }
}
