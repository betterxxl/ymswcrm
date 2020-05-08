package com.ymsw;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

public class test {
    public static void main(String[] args) {
        String datestr="2020-04-28 14:30:15";
        DateTime dateTime = DateUtil.parse(datestr);
        System.out.println("dateTime = " + dateTime);
    }
}
