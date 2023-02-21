package com.realblox.dimetime.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils {
	
	private static Map<String, DateFormat> formatters = new HashMap<String, DateFormat>();
	
	private static String defaultFormatString = "yyyyMMddHHmmss";
	private static DateFormat defaultFormatter = new SimpleDateFormat(defaultFormatString);
	
	private static String getString(String val) {
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}
	
	public synchronized static String getTime(Date date) {
		return defaultFormatter.format(date);
	}

	public synchronized static Date getTime(String date) throws ParseException {
		return defaultFormatter.parse(date);
	}
	public synchronized static String getTime(Date date, String format) {
		if (date == null) {
			return null;
		}
		return getFormatter(format).format(date);
	}

	public synchronized static Date getTime(String date, String format) throws ParseException {
		if (date == null || date.length() == 0) {
			return null;
		}
		return getFormatter(format).parse(date);
	}

	public synchronized static String getTime(String date, String beforeFormat, String afterFormat) throws ParseException {
		return getTime(getTime(date, beforeFormat), afterFormat);
	}
	
	private static String formatDateTime(String format, Timestamp timestamp) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(timestamp);
	}

	private static String formatDate(String format) {
		GregorianCalendar gc = new GregorianCalendar();
	    SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
	    Date d = gc.getTime();
	    String str = sf.format(d);
	    return str.substring(0,4)+ format +str.substring(4,6)+ format +str.subSequence(6, 8) ;  
	}
	
	private static Timestamp getDateTime() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	private static Timestamp getDateTime(int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getDateTime());
		
		if (days != 0) {
			cal.add(Calendar.DATE, days);
		}
		
		return new Timestamp(cal.getTimeInMillis());
	}
	
	public static String getCurrentDate() {
		return getCurrentDate(null);
	}

	public static String getCurrentDateTime() {
		return getCurrentDateTime(null);
	}
	
	public static String getCurrentDate(String format) {
		if (format == null || "".equals(format)) {
			format = "";
		} 
		return formatDate(format);
	}

	public static String getCurrentDateTime(String format) {
		if (format == null || "".equals(format)) {
			format = "yyyyMMddHHmmss";
		} 
		return formatDateTime(format, getDateTime());
	}
	
	public static String getAfterDateTime(String format, int days) {
		return formatDateTime(format, getDateTime(days));
	}
	
	public static String addMonth(String paramString, int paramInt){
	    if (paramString.length() != 8)
	      new Exception("잘못된 날짜형식입니다.(8자리) : " + paramString);
	    int i = Integer.parseInt(paramString.substring(0, 4));
	    int j = Integer.parseInt(paramString.substring(4, 6)) - 1;
	    int k = Integer.parseInt(paramString.substring(6, 8));
	    Calendar localCalendar = Calendar.getInstance();
	    localCalendar.set(i, j, k);
	    localCalendar.add(2, paramInt);
	    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		return localSimpleDateFormat.format(localCalendar.getTime());
	}
	
	/** 
	 * 해당 포맷의 문자열을 Date로 반환.
	 * @param textDate
	 * @param formatStr
	 * @return
	 */
	public static Date getStringToDate(String textDate, String formatStr) {
		SimpleDateFormat format = new SimpleDateFormat( formatStr  ); //"yyyy-MM-dd");
		try {
			return format.parse(textDate);
		} catch(Exception e) {
			return new Date();
		}
	}

	/**
	 * Date 를 해당 포맷의 문자열로 반환.
	 * @param tDate
	 * @param formatStr
	 * @return
	 */
	public static String getDateToString(Date tDate, String formatStr) {
		SimpleDateFormat format = new SimpleDateFormat( formatStr  ); //"yyyy-MM-dd");
		try {
			return format.format(tDate);
		} catch(Exception e) {
			return getCurrentDate(formatStr);
		}
	}

	private static DateFormat getFormatter(String format) {
		DateFormat formatter = formatters.get(format);
		if (formatter == null) {
			formatter = new SimpleDateFormat(format);
			formatters.put(format, new SimpleDateFormat(format));
		}
		return formatter;
	}

	public static String addDate(String date, int days, String format) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getFormatter(format).parse(date));
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + days);
		return getFormatter(format).format(calendar.getTime());
	}

	public synchronized static String addTime(String date, int hours, int minutes, int seconds, String format) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(getFormatter(format).parse(date));
			calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + hours);
			calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + minutes);
			calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + seconds);
			return getFormatter(format).format(calendar.getTime());
		} catch (Exception e) {
			return date;
		}
	}

	public synchronized static boolean isNextDay(String day, String pattern) {
		boolean retBool = false;

		try {

			String toDay = getCurrentDateTime(pattern);

			Date dateToday = getStringToDate(toDay, pattern);
			Date dateReqDay = getStringToDate(day + "", pattern);

			retBool = dateToday.before(dateReqDay);

			if(!retBool)
				retBool = retBool;
		} catch (Exception e) {
			e.printStackTrace();
			retBool = false;
		}

		return retBool;
	}

	public static boolean isDate(String day, String pattern) {

		boolean retBool = true;

		try {
			// SimpleDateFormat 생성
			SimpleDateFormat format = new SimpleDateFormat( pattern );
			
			// 날짜형식에 대해서 관대하게 허용하지 않음 (예) 2014-02-33 같은 날짜를 허용하지 못하도록 함
			format.setLenient(false);
			format.parse(day);

			if(pattern.length() != day.length()){
				retBool = false;
			}
			
		} catch (Exception e) {
			retBool = false;
		}
		return retBool;
	}
	public static String DateFormatChg(String yyyymmdd, String beforePattern, String afterPattern) {
		String retDate = "";
		retDate = getDateToString(getStringToDate(yyyymmdd, beforePattern),afterPattern);
		return retDate;
	}
}
