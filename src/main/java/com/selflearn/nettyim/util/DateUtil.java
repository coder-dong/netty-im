package com.selflearn.nettyim.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by coding-dong on 2018/11/4.
 */
public class DateUtil {

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd hh:mm:ss";

    public static String getNowWithYYYYMMDDHHMMSS(){
        DateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        return dateFormat.format(new Date());
    }
}
