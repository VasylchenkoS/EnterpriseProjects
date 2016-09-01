package com.vasylchenko.gui.util;

import java.sql.Date;

public class DateUtil {

    public static Date parseString(String date){
        try {
            return Date.valueOf(date);
        }catch (IllegalArgumentException e){
            throw new RuntimeException("Wrong input data");
        }
    }
}
