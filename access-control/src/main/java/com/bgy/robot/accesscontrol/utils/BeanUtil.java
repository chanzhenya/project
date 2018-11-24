package com.bgy.robot.accesscontrol.utils;

import java.io.*;

public class BeanUtil {

    /**
     * 对象转字节数组
     * @param obj
     * @return
     */
    public static byte[] ObjectToBytes(Object obj){
        byte[] bytes = null;
        ByteArrayOutputStream bo = null;
        ObjectOutputStream oo = null;
        try {
            bo = new ByteArrayOutputStream();
            oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);
            bytes = bo.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(bo!=null){
                    bo.close();
                }
                if(oo!=null){
                    oo.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    /**
     * 字节数组转对象
     * @param bytes
     * @return
     */
    public static Object BytesToObject(byte[] bytes){
        Object obj = null;
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            byteArrayInputStream =new ByteArrayInputStream(bytes);
            objectInputStream =new ObjectInputStream(byteArrayInputStream);
            obj = objectInputStream.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(byteArrayInputStream!=null){
                    byteArrayInputStream.close();
                }
                if(objectInputStream!=null){
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return obj;
    }
}
