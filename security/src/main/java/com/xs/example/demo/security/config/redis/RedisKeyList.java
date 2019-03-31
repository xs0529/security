package com.xs.example.demo.security.config.redis;

/**
 * <p>
 *
 * </p>
 *
 * @author Wangchenghong
 * @Date 2019/2/28
 */
public class RedisKeyList {

    /**
     * 获取当前用户所在驻地id
     * @param userId
     * @return
     */
    public static String getStationId(int userId) {
        return "userid:"+userId+":stationid:";
    }
}
