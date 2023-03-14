package com.chang.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class SignService {

    @Autowired
    private RedisTemplate redisService;


    private int dayOfMonth() {
        DateTime dateTime = new DateTime();
        return dateTime.dayOfMonth().get();
    }

    /**
     * 按照月份和用户ID生成用户签到标识 UserId:Sign:001:2022-11
     * @param userId 用户id
     * @return
     */
    private String signKeyWitMouth(String userId) {
        DateTime dateTime = new DateTime();
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM");
        StringBuilder builder = new StringBuilder("UserId:Sign:");
        builder.append(userId).append(":")
                .append(dateTime.toString(fmt));
        return builder.toString();
    }
    
    /**
     * 设置标记位
     * 标记是否签到
     * @param key
     * @param offset
     * @param tag
     * @return
     */
    public Boolean mark(String key, long offset, boolean tag) {
        return redisService.opsForValue().setBit(key, offset, tag);
    }


    /**
     * 统计计数
     * @param key 用户标识
     * @return
     */
    public long bitCount(String key) {
        return (long)redisService.execute((RedisCallback<Long>) redisConnection -> redisConnection.bitCount(key.getBytes()));
    }

	/**
	 * 获取多字节位域 
	 */
    public List<Long> bitField(String buildSignKey, int limit, long offset) {
        return redisService
                .opsForValue()
                .bitField(buildSignKey, BitFieldSubCommands.create().get(BitFieldSubCommands.BitFieldType.unsigned(limit)).valueAt(offset));
    }


    /**
     * 判断是否被标记
     * @param key
     * @param offset
     * @return
     */
    public Boolean container(String key, long offset) {
        return redisService.opsForValue().getBit(key, offset);
    }


    /**
     * 用户今天是否签到
     * @param userId
     * @return
     */
    public boolean checkSign(String userId) {
        DateTime dateTime = new DateTime();
        String signKey = this.signKeyWitMouth(userId);
        int offset = dateTime.getDayOfMonth() - 1;
        int value = this.container(signKey, offset) ? 1 : 0;
        return value == 1;
    }


    /**
     *  查询用户当月签到日历
     * @param userId
     * @return
     */
    public LinkedHashMap<String, Boolean> querySignedInMonth(String userId) {
        DateTime dateTime = new DateTime();
        int lengthOfMonth = dateTime.dayOfMonth().getMaximumValue();
        LinkedHashMap<String, Boolean> res = new LinkedHashMap<>();
        String signKey = this.signKeyWitMouth(userId);
        List<Long> bitField = this.bitField(signKey, lengthOfMonth, 0);
        if (!CollectionUtils.isEmpty(bitField)) {
            long signFlag = bitField.get(0) == null ? 0 : bitField.get(0);
            DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
            for (int i = lengthOfMonth; i > 0; i--) {
                DateTime dateTime1 = dateTime.withDayOfMonth(i);
                res.put(dateTime1.toString(fmt), signFlag >> 1 << 1 != signFlag);
                signFlag >>= 1;
            }
        }
        return res;
    }


    /**
     *  用户签到
     * @param userId
     * @return
     */
    public boolean signWithUserId(String userId) {
        int dayOfMonth = this.dayOfMonth();
        String signKey = this.signKeyWitMouth(userId);
        long offset = (long)dayOfMonth - 1;
        if (Boolean.TRUE.equals(this.mark(signKey, offset, Boolean.TRUE))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *  统计当前月份一共签到天数
     * @param userId
     */
    public long countSignedInDayOfMonth(String userId) {
        String signKey = this.signKeyWitMouth(userId);
        return this.bitCount(signKey);
    }

 
    /**
     *  查询用户当月连续签到次数
     * @param userId
     * @return
     */
    public long queryContinuousSignCountOfMonth(String userId) {
        int signCount = 0;
        String signKey = this.signKeyWitMouth(userId);
        int dayOfMonth = this.dayOfMonth();
        List<Long> bitField = this.bitField(signKey, dayOfMonth, 0);
        if (!CollectionUtils.isEmpty(bitField)) {
            long signFlag = bitField.get(0) == null ? 0 : bitField.get(0);
            DateTime dateTime = new DateTime();
            // 连续不为0即为连续签到次数，当天未签到情况下
            for (int i = 0; i < dateTime.getDayOfMonth(); i++) {
                if (signFlag >> 1 << 1 == signFlag) {
                    if (i > 0) break;
                } else {
                    signCount += 1;
                }
                signFlag >>= 1;
            }
        }
        return signCount;
    }


   /**
     * 以7天一个周期连续签到次数
     * @param period 周期
     * @return
     */
    public long queryContinuousSignCount(String userId,Integer period){
        //查询目前连续签到次数
        long count = this.queryContinuousSignCountOfMonth(userId);
        //按最大连续签到取余
        if(period != null && period < count){
            long num = count % period;
            if(num == 0){
                count = period;
            }else{
                count = num;
            }
        }
        return count;
    }
}

