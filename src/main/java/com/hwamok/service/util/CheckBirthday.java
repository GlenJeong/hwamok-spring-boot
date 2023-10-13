package com.hwamok.service.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckBirthday {

    public static final String REGEXP_DATE_TYPE1 = "^[\\d]{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";


    public static Date StringToDateConversion(String birthday) throws ParseException {

        boolean format = birthday.matches(CheckBirthday.REGEXP_DATE_TYPE1);
        if(format == true) {
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format1.parse(birthday);
            return date;
        } else {
            return new Date();
        }

    }

    public static String DateToStringConversion(Date birthday) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(birthday);

        System.out.println(" DateToStringConversion date.getClass() = " + date.getClass());
        System.out.println(" DateToStringConversion date = " + date);

        return date;
    }



}
