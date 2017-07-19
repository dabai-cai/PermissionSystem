package cn.scau.hjr.controller;

import cn.scau.hjr.util.ShiroUtils;

/**
 * Created by Administrator on 2017/7/19 0019.
 */
public class test {
    public static void main(String[] args) {
        System.out.println(ShiroUtils.encodeToString("1"));
        System.out.println(ShiroUtils.deCodeToString("VFZFOVBRPT0="));
    }
}
