package zz.mk.utilslibrary;

import java.text.SimpleDateFormat;

/**
 * 处理时间的工具类
 *
 */
public class DateUtil {


    /**
     * @param time
     * @param format
     * @return Modifier： Modified Date： Modify：
     */
    public static String getTime(String time, String format) {

        try {
            long timeLong = Long.parseLong(time);
            return getTime(timeLong,format);
        }catch (NumberFormatException e){
            return time;
        }
    }

    /**
     * 根据时间戳获取标准时间
     *
     * @param time
     * @return"yyyy-MM-dd "HH:mm:ss"
     */
    public static String getTime(long time, String formatStyel) {
        SimpleDateFormat ft = new SimpleDateFormat(formatStyel);
        return ft.format(time);
    }

    /**
     * 获取时间戳 转化成的天数（取整）
     *
     * @param time
     * @return
     */
    public static String getDaySum(long time) {
        return String.valueOf((int) Math.ceil(time / (3600 * 1000 * 24)));
    }

    /**
     * 将毫秒数换算成x天x时x分
     */
    public static String getDayHourMinute(long time) {
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = time / dd;
        long hour = (time - day * dd) / hh;
        long minute = (time - day * dd - hour * hh) / mi;
//        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
//        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

//        String strDay = day < 10 ? "0" + day : "" + day;
        String strDay = day + "";
        String strHour = hour < 10 ? "0" + hour : "" + hour;
        String strMinute = minute < 10 ? "0" + minute : "" + minute;
//        String strSecond = second < 10 ? "0" + second : "" + second;
//        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;
//        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;
        return strDay + "天" + strHour + "时" + strMinute + "分";
    }

    /**
     * 将毫秒数换算成x天x时x分x秒
     */
    public static String getDayHourMinuteSecond(long time) {
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = time / dd;
        long hour = (time - day * dd) / hh;
        long minute = (time - day * dd - hour * hh) / mi;
        long second = (time - day * dd - hour * hh - minute * mi) / ss;

        String strDay = day + "";
        String strHour = hour < 10 ? "0" + hour : "" + hour;
        String strMinute = minute < 10 ? "0" + minute : "" + minute;
        String strSecond = second < 10 ? "0" + second : "" + second;
        return strDay + "天" + strHour + "时" + strMinute + "分" + strSecond + "秒";
    }
    /**
     * 将毫秒数换算x时x分x秒x毫秒
     */
    public static String getHourMinuteSecond(long time) {
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;

        long hour = time / hh;
        long minute = (time - hour * hh) / mi;
        long second = (time - hour * hh - minute *mi)/ss;

        String strHour = hour < 10 ? "0" + hour : String.valueOf(hour);
        String strMinute = minute < 10 ? "0" + minute : String.valueOf(minute);
        String strSecond = second <10 ? "0" +second : String.valueOf(second);
        return strHour + ":" + strMinute + ":" + strSecond ;
    }
    /**
     * 将毫秒数换算x时x分x秒x毫秒
     */
    public static String getHourMinuteSecond(String time) {
        try {
            long timeLong = Long.parseLong(time);
            return getHourMinuteSecond(timeLong);
        }catch (NumberFormatException e){
            return time;
        }
    }
}