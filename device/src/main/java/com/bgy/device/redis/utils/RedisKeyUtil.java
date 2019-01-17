package com.bgy.device.redis.utils;

/**
 * @author Judith
 * @date 2019/1/14 10:46
 * @description
 */
public class RedisKeyUtil {

    /**
     * redis的key
     * 形式为：
     * 表名：主键名：主键值：列名
     *
     * @param tableName 表名
     * @param majorKey 主键名
     * @param majorKeyValue 主键值
     * @param column 列名
     * @return
     */
    public static String getKeyWithColumn(String tableName, String majorKey, String majorKeyValue, String column) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(tableName).append(":");
        buffer.append(majorKey).append(":");
        buffer.append(majorKeyValue).append(":");
        buffer.append(column);
        return buffer.toString();
    }

    /**
     * redis的key
     * 形式为：
     * 表名:主键名:主键值
     *
     * @param tableName 表名
     * @param majorKey 主键名
     * @param majorKeyValue 主键值
     * @return
     */
    public static String getKey(String tableName,String majorKey,String majorKeyValue){
        StringBuffer buffer = new StringBuffer();
        buffer.append(tableName).append(":");
        buffer.append(majorKey).append(":");
        buffer.append(majorKeyValue).append(":");
        return buffer.toString();
    }
    /**
     * 
     * <p>Title: getTokenKey</p>  
     * <p>Description:获取Redis token Redis key  </p>  
     * @param token
     * @return String
     */
    public static String getTokenKey(String token){
        return "LOGIN_TOKEN_" + token;
    }

    /**
     * 获取存在Redizs中用户的key
     * @param username
     * @return
     */
    public static String getUserKey(String username) {
        return "USER_TOKEN_"+username;
    }
}
