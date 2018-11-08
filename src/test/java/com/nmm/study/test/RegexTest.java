package com.nmm.study.test;

import java.util.regex.Pattern;

public class RegexTest {

    public static void main(String[] args) {
        //人员信息
        String str= "杨帅158**1606";
        Pattern pattern = Pattern.compile("\\D+\\d+\\*+\\d+");
        System.out.println(pattern.matcher(str).find());
        pattern = Pattern.compile("\\d+\\.\\d+发货");
        str = "多效马油修护面霜11.17发货";
        System.out.println(pattern.matcher(str).find());
    }
}
