package com.nmm.study.word.controller;

import com.nmm.study.word.config.SysProp;
import com.nmm.study.word.mode.OrderInfo;
import com.nmm.study.word.service.AipService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 转换接口
 */
@Controller
public class ContextController {
    @Autowired
    private SysProp sysProp;
    @Autowired
    private AipService aipService;
    //人员匹配规则
    private Pattern user = Pattern.compile("\\d{3}.+\\d{4}");
    //商品发货匹配规则
    private Pattern product = Pattern.compile("\\d+\\.?\\d+发货");
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 将源目标文件内容转为相关文字内容。
     * @return
     */
    @ResponseBody
    @RequestMapping("/words/convert")
    public String convertPic2Word(){
        File source = new File(sysProp.getSourceDir());
        File target = new File(sysProp.getTaregtDir());
        File[] sourcePics = source.listFiles();
        List<OrderInfo> infos = new ArrayList<OrderInfo>();
        //遍历处理，这里不做深入处理，简单的单层处理
        for (File pic : sourcePics) {
            String name = pic.getName();
            String pdname = name.substring(name.indexOf(".")+1);
            if (pdname.equalsIgnoreCase("png")||
                    pdname.equalsIgnoreCase("jpg")||
                    pdname.equalsIgnoreCase("jpeg")) {
                logger.info("开始转换：" + name);
                JSONObject res = aipService.sendRequestByUrl(pic.getAbsolutePath());
                //处理逻辑
                JSONArray array = res.getJSONArray("words_result");
                OrderInfo info = new OrderInfo();
                infos.add(info);
                for (int i = 0; i < array.length();i++) {
                    String word = array.getJSONObject(i).getString("words");
                    //处理逻辑
                    Matcher matcher = user.matcher(word);
                    if (matcher.find()) {//收货人
                        String phone = matcher.group();
                        info.setUsername(word.substring(0,word.length() - phone.length()));
                    }
                    matcher = product.matcher(word);
                    if (matcher.find()) {//商品
                        String pro = matcher.group();
                        info.setProduct(word.substring(0,word.length() - pro.length()));
                        info.setSendTime(pro.replace("发货",""));
                    }
                    if (word.contains("订单编号")){
                        info.setOrderNo(word.substring(word.indexOf(":")+1));
                    }
                    if (word.contains("创建时间")) {
                        info.setCreateTime(word.substring(word.indexOf(":")+1));
                    }
                }
                pic.renameTo(new File(target,name));
                logger.info("转换完成。" );
            }else {
                logger.info("不支持的文件：" + name);
            }
        }
        logger.info("识别结果：" + infos);
        return "成功了";
    }

}
