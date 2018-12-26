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
            "/etc/init.d/schedulecloud start","/etc/init.d/schedulecloud stop","/etc/init.d/schedulecloud restart");
}
