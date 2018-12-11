package com.example.divyanshsingh.transportationmanagement.utils;

import java.util.Calendar;

public class DateTimeUtils {
    public static String getCurrentDateId() {
        String m, d, hr, min, se, reString="";
        final Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        if (day < 10) {
            reString = reString + "0" + String.valueOf(day);
        } else {
            reString = reString + String.valueOf(day);
        }
        int month = c.get(Calendar.MONTH) + 1;
        if (month < 10) {
            reString = reString + "0" + String.valueOf(month);
        } else {
            reString = reString + String.valueOf(month);
        }
        int year = c.get(Calendar.YEAR);
        reString = String.valueOf(year);
        return reString;
    }

    public static String getCurrentFormattedDate() {
        final Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_WEEK);
        return String.valueOf(day) + ", " + getDay() + "/" + getMonth();
    }

    public static String getCurrentTime(){
        String time = getHour() + ":" + getMinute();
        return time;
    }
    public static String getCurrentTimeId(){
        String time = getHour()+getMinute()+"00";
        return time;
    }

    public static String getHour() {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);

        if (hour < 10) {
            return "0" + String.valueOf(hour);
        }
        return String.valueOf(hour);
    }

    public static String getMinute() {
        final Calendar c = Calendar.getInstance();
        int minute = c.get(Calendar.MINUTE);
        if (minute < 10) {
            return "0" + String.valueOf(minute);
        }
        return String.valueOf(minute);
    }

    public static String getSecond() {
        final Calendar c = Calendar.getInstance();
        int sec = c.get(Calendar.SECOND);
        if (sec < 10) {
            return "0" + String.valueOf(sec);
        }
        return String.valueOf(sec);
    }

    public static String getDay() {
        final Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        return String.valueOf(day);
    }

    public static String getMonth() {
        final Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH);
        return String.valueOf(month);

    }

    public static String getYear() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        return String.valueOf(year);
    }


    public static int getHour(int i) {
        final Calendar c = Calendar.getInstance();
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute(int i) {
        final Calendar c = Calendar.getInstance();
        return c.get(Calendar.MINUTE);
    }

    static public int getSecond(int i) {
        final Calendar c = Calendar.getInstance();
        return c.get(Calendar.SECOND);
    }

    public static int getDay(int i) {
        final Calendar c = Calendar.getInstance();
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public static int getMonth(int i) {
        final Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH);

    }

    public static int getYear(int i) {
        final Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }

    public static String returnReverse(String in) {
        String temp = in.replaceAll("/", "");
        byte[] strAsByteArray = temp.getBytes();

        byte[] result =
                new byte[strAsByteArray.length];

        // Store result in reverse order into the
        // result byte[]
        for (int i = 0; i < strAsByteArray.length; i++)
            result[i] =
                    strAsByteArray[strAsByteArray.length - i - 1];

        return new String(result);

    }
}
