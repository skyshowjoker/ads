package com.example.ads.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    // 日期格式字符串
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final String DEFAULT_STRING_FORMAT = "yyyy-MM-dd";


    // 将日期字符串解析为 Date 对象
    public static Date parseDate(String dateStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        return dateFormat.parse(dateStr);
    }

    // 将日期字符串按指定格式解析为 Date 对象
    public static Date parseDate(String dateStr, String dateFormatStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
        return dateFormat.parse(dateStr);
    }

    // 格式化 Date 对象为日期字符串
    public static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_STRING_FORMAT);
        return dateFormat.format(date);
    }

    // 格式化 Date 对象为日期字符串，使用指定格式
    public static String formatDate(Date date, String dateFormatStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
        return dateFormat.format(date);
    }

    public static void main(String[] args) {
        try {
            // 解析日期字符串为 Date 对象
            String dateStr = "2023-09-15 14:30:00";
            Date date = parseDate(dateStr);
            System.out.println("Parsed Date: " + date);

            // 格式化 Date 对象为日期字符串
            String formattedDate = formatDate(date);
            System.out.println("Formatted Date: " + formattedDate);

            // 指定自定义日期格式解析和格式化
            String customDateFormatStr = "dd/MM/yyyy HH:mm";
            Date customDate = parseDate("20/10/2023 10:45", customDateFormatStr);
            System.out.println("Custom Parsed Date: " + customDate);

            String customFormattedDate = formatDate(customDate, customDateFormatStr);
            System.out.println("Custom Formatted Date: " + customFormattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
