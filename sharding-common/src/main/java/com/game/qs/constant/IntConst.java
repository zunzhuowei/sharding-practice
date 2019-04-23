package com.game.qs.constant;

/**
 * Created by zun.wei on 2018/8/5.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public interface IntConst {

    int SECOND = 1;
    int MINUTE = 60 * SECOND;
    int HOUR = 60 * MINUTE;
    int DAY = 24 * HOUR;
    int MONTH = 30 * DAY;
    int YEAR = 12 * MONTH;


    // 手机号码长度
    int PHONE_LEN = 11;

    // 密码最短长度
    int PASSWORD_LESS_LEN = 6;

    // 密码最长长度
    int PASSWORD_MORE_LEN = 24;

}
