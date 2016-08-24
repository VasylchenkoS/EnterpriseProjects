package com.vasylchenko.gui.util;

import java.sql.Date;

public class DateUtil {

    public static Date parseString(String date){
        return Date.valueOf(date);
    }
}
