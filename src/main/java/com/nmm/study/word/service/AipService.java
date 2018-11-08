package com.nmm.study.word.service;

import com.baidu.aip.ocr.AipOcr;
import com.nmm.study.word.Variable;
import org.json.JSONObject;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * 参考api，根据官网介绍jpg格式的图片识别率更高
 * 返回每一行的置信度
 * https://cloud.baidu.com/doc/OCR/OCR-Java-SDK.html#.E9.80.9A.E7.94.A8.E6.96.87.E5.AD.97.E8.AF.86.E5.88.AB
 * 文字识别service
 */
@Service
public class AipService implements InitializingBean{

    private AipOcr client;
    private HashMap<String,String> options;

    /**
     * 根据传入的图片地址，可以是本地的也可以是远程地址，调用百度api识别文字
     * @param path
     * @return
     */
    public JSONObject sendRequestByUrl(String path) {
        //该api也接受数组识别，传入图片的二进制数组。
        JSONObject res = client.basicGeneral(path,options);
        System.out.println(res.toString(2));
        return res;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        client = new AipOcr(Variable.APP_ID,Variable.API_KEY,Variable.SECRET_KEY);
        client.setConnectionTimeoutInMillis(100000);
        client.setSocketTimeoutInMillis(60000);
        options = new HashMap<String, String>();
        //配置信息
        options.put("language_type","CHN_ENG");//支持的语言
        options.put("detect_direction","true");//是否检测图片朝向
        options.put("detect_language","true");//是否检测语言，默认不检测
        options.put("probability","true");//返回每一行的置信度
    }
}
