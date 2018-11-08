package com.nmm.study.word;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;

import java.util.HashMap;

public class Demo {

    public static void main(String[] args) {
        //初始化aip客户端
        AipOcr client = new AipOcr(Variable.APP_ID,Variable.API_KEY,Variable.SECRET_KEY);
        //可选设置，设置超时时间，设置一个即可
        client.setConnectionTimeoutInMillis(20000);
        client.setSocketTimeoutInMillis(60000);
        //设置带来
//        client.setHttpProxy();
//        client.setSocketProxy();
        //可选配置log4j输出
        //调用接口
        String path = "F:\\weixin/test.png";
        JSONObject res = client.basicGeneral(path,new HashMap<String, String>());
        System.out.println(res.toString(2));
    }


}
