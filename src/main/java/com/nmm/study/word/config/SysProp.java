package com.nmm.study.word.config;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Data
@Service
public class SysProp  implements InitializingBean{
    @Value("${words.sourceDir}")
    private String sourceDir;
    @Value("${words.targetDir}")
    private String taregtDir;
    @Value("${words.excelDir}")
    private String excelDir;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (sourceDir == null) {
            sourceDir = "F:/sourceDir";
        }
        if (taregtDir == null) {
            taregtDir = "F:/targetDir";
        }
        if (excelDir == null) {
            excelDir = "F:/excelDir";
        }
        File source = new File(sourceDir);
        if (!source.exists()) {
            source.mkdirs();
        }
        File target = new File(taregtDir);
        if (!target.exists()) {
            target.mkdirs();
        }
        File excel = new File(excelDir);
        if (!excel.exists()) {
            excel.mkdirs();
        }
    }
}
