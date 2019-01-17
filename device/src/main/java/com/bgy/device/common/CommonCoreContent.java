package com.bgy.device.common;

import java.util.Arrays;
import java.util.List;

/**
 * @author Judith
 * @date 2018/12/14
 */
public class CommonCoreContent {

    public static String BRANCH_ID = "99c91fb7db624279921772f31cdad4a9";

    public static String SYNC_URL = "https://bzl.yeehot.cn/canteen/device/sync";

    public static List<String> cmdList = Arrays.asList("/etc/init.d/dispatchSys start","/etc/init.d/dispatchSys stop","/etc/init.d/dispatchSys restart",
            "/etc/init.d/schedulecloud start","/etc/init.d/schedulecloud stop",
            "/etc/init.d/schedulecloud restart","/etc/init.d/plcCOM start", "/etc/init.d/plcCOM stop",
            "/etc/init.d/plcCOM restart");

    public static final String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJALZrnvYa7tMDbYuMHD8XGXiZ+hxnpFNlUcHx5CIRqRJkjcf7CAgYMoxG903MIbnWE0qJ6VNIdSg7Yjuj3o+ydi94MKFhMR0SNRqRvQZeGzDS6s6QUkPv8hVnWJoQA+4r24N+Sq/nlTn2BqsfjHvYF5u4R3Q9oBgHnBrRGvqzZDAgMBAAECgYAHXp3121p7EaWK2NGzECxVLHSdFzDJZqsd9Z9d8/Ivosd7qNRbmmeF51sNfMQipjkKFSCfZHDYj48vjWpk6nuW929eFsKyrTilI4OWe3eoplH/Q9Qd1BQs6AG4YUptFK0nZJmFx6hNFlDltQbB9CNC+VdP3n3qJPqkCpN8yBz5gQJBAN1GE02sazw123JUT8erke81s7vcLauEBCEdgXQCL210VSgua4bZNmgx5J+xbE25gQPTHvZkE16/QXYo9yR2RgMCQQCmpo4JdzZimX9KPdDqjxa7xo/zdtdN0lg1shc0U+Nwhpsf9ltZ47JXxNNfKT6bq1n4+mYhEmxmEsJ4mSYaYXrBAkEA1w7UlBmP3+U4c9Yd9CJ8o9G97kgA20JkAQt8VhQJpIf8fCEhwN48UuirG8qFSMWS7vSykyaQ7PVV2lHWYknRoQJAZ+xzpPl/o8s/MugLsUpcDetMd7JDn9cthQazK8GoktKOSEdUPmEdJViry6snzOH1cKuOJs+bY6gNODGhLlBdwQJAYZ+gtQKKRmx0yK2E1c1knYYBgWPvwMBHY/AGjCrSePOk1lgOnikckOITQJYCZ0V+4AVJDqy4Brd84JRma3N3Vw==";
    
    public static final String LOGIN_CACHE_PREFIX = "LOGIN_SMSCODE_";
	
	//暂时动态短信5分钟有效 单位秒 可从配置文件获取
    public static final long SMSCODE_EXPIRE_SECONDS = 60 * 5;
	
	//登录有效期 单位秒 可从配置文件获取
    public static final long  LOGIN_EXPIRE_TIME = 60 * 60;
	
	//浏览器token key 可从配置文件获取
    public static final String TOKEN_KEY = "DEVICE_TOKEN";
}
