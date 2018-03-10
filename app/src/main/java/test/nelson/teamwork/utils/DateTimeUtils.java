package test.nelson.teamwork.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by nelsonnwezeaku on 3/9/18.
 */

public class DateTimeUtils {

    public static final String DATE_FORMAT_1 = "yyyy-dd-mm'T'HH:mm:ss";
    public static final String DATE_FORMAT_2 = "yyyyMMdd";
    public static final String DATE_FORMAT_3 = "HH:mm";

    public static final String DATE_FORMAT_SHORT = "dd MMM yyyy";


    public static String getUpdateDateNow() {
        return dateToString(new Date(), TimeZone.getTimeZone("UTC"), DATE_FORMAT_2);
    }

    public static String getUpdateTimeNow() {
        return dateToString(new Date(), TimeZone.getTimeZone("UTC"), DATE_FORMAT_3);
    }

    private static Date serverStringToDate(String input, TimeZone timeZone, String format) {
        SimpleDateFormat sourceFormat = new SimpleDateFormat(format, Locale.getDefault());
        sourceFormat.setTimeZone(timeZone);

        try {
            return sourceFormat.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static String dateToString(Date input, TimeZone timeZone, String formatTemplate) {
        SimpleDateFormat sourceFormat = new SimpleDateFormat(formatTemplate, Locale.getDefault());
        sourceFormat.setTimeZone(timeZone);
        return sourceFormat.format(input);
    }

    public static String stringToDateFormat1(String input, String format) {
        if (TextUtils.isEmpty(input)) return "Unknown"; //Quick fix.
        return formatDateTime(serverStringToDate(input, TimeZone.getTimeZone("UTC"), DATE_FORMAT_1), format);

    }

    public static String stringToDateFormat2(String input, String format) {
        if (TextUtils.isEmpty(input)) return "Unknown"; //Quick fix. Should be improved
        return formatDateTime(serverStringToDate(input, TimeZone.getTimeZone("UTC"), DATE_FORMAT_2), format);

    }


    private static String formatDateTime(Date input, String format) {
        return dateToString(input, TimeZone.getTimeZone("UTC"), format);
    }


}
