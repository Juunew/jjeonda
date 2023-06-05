package com.fintech.jjeondaproject.util;

import java.io.FileInputStream;
import java.util.Properties;

public class MyConfigReader {
	public Properties readConfig(String configPath){
        Properties prop = new Properties();
        try{
            // 프로퍼티 파일 스트림에 담기
            FileInputStream fis = new FileInputStream(configPath);

            // 프로퍼티 파일 로딩
            prop.load(new java.io.BufferedInputStream(fis));
        } catch(Exception e){
            throw new RuntimeException(e);
        }
        return prop;
    }
}
