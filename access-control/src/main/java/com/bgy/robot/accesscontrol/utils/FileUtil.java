package com.bgy.robot.accesscontrol.utils;

import java.io.File;
import java.io.FileOutputStream;

public class FileUtil {

    public static void uploadFile(byte[] file,String filePath,String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()) {
            targetFile.mkdirs();
        }

        FileOutputStream outputStream = new FileOutputStream(filePath+fileName);
        outputStream.write(file);
        outputStream.flush();
        outputStream.close();
    }
}
