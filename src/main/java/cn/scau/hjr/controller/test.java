package cn.scau.hjr.controller;

import cn.scau.hjr.util.shiroUtil;

import javax.swing.*;

/**
 * Created by Administrator on 2017/7/23 0023.
 */
public class test {
    public static void main(String[] args) {
        System.out.println(shiroUtil.encode("1","651839985 dsf"));
        JOptionPane.showMessageDialog(null, "input error format", "error", JOptionPane.ERROR_MESSAGE);
    }
}
