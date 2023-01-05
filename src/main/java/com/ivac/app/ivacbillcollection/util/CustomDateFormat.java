package com.ivac.app.ivacbillcollection.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateFormat {
    public static boolean compareDates(String d1, String d2) {
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = sdf.parse(d1);
            Date date2 = sdf.parse(d2);

            if (date1.after(date2)) {
                return true;
            } else if (date1.compareTo(date2) == 0) {
                return true;
            }


        } catch (ParseException ex) {
            return false;
        }
        return false;
    }
}
