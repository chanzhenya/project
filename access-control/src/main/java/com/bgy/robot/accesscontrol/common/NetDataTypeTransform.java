package com.bgy.robot.accesscontrol.common;

import java.io.UnsupportedEncodingException;

public class NetDataTypeTransform {

    //全局定义，以适应系统其他部分
    public static final String coding="GB2312";

    public NetDataTypeTransform(){}

    /**
     * 将int、long转为低字节在前，高字节在后的byte数组
     * @param n
     * @return
     */
    public byte[] IntToByteArray(int n) {
        byte[] b = new byte[4];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        b[2] = (byte) (n >> 16 & 0xff);
        b[3] = (byte) (n >> 24 & 0xff);
        return b;
    }

    public byte[] LongToByteArray(long n) {
        byte[] b = new byte[8];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        b[2] = (byte) (n >> 16 & 0xff);
        b[3] = (byte) (n >> 24 & 0xff);
        b[4] = (byte) (n >> 32 & 0xff);
        b[5] = (byte) (n >> 40 & 0xff);
        b[6] = (byte) (n >> 48 & 0xff);
        b[7] = (byte) (n >> 56 & 0xff);
        return b;
    }


    /**
     * byte数组转化为int、long
     * 将低字节在前转为int、long，高字节在后的byte数组
     * @param bArr
     * @return
     */
    public int ByteArrayToInt(byte[] bArr) {
        if(bArr.length!=4){
            return -1;
        }
        return (int) ((((bArr[3] & 0xff) << 24)
                | ((bArr[2] & 0xff) << 16)
                | ((bArr[1] & 0xff) << 8) | ((bArr[0] & 0xff) << 0)));
    }

    public long ByteArrayToLong(byte[] bArr) {
        if(bArr.length!=8){
            return -1;
        }
        return (long) ((((bArr[7] & 0xff) << 56)
                | ((bArr[6] & 0xff) << 48)
                | ((bArr[5] & 0xff) << 40)
                | ((bArr[4] & 0xff) << 32)
                | ((bArr[3] & 0xff) << 24)
                | ((bArr[2] & 0xff) << 16)
                | ((bArr[1] & 0xff) << 8)
                | ((bArr[0] & 0xff) << 0)));
    }

    /**
     * 将byte数组转化成String,为了支持中文，转化时用GBK编码方式
     * @param valArr
     * @param maxLen
     * @return
     */
    public String ByteArraytoString(byte[] valArr,int maxLen) {
        String result=null;
        int index = 0;
        while(index < valArr.length && index < maxLen) {
            if(valArr[index] == 0) {
                break;
            }
            index++;
        }
        byte[] temp = new byte[index];
        System.arraycopy(valArr, 0, temp, 0, index);
        try {
            result= new String(temp,"GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 将String转化为byte,为了支持中文，转化时用GBK编码方式
     * @param str
     * @return
     */
    public byte[] StringToByteArray(String str){
        byte[] temp = null;
        try {
            temp = str.getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return temp;
    }
}
