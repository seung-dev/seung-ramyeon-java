package seung.kimchi.java;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class SDate {

	public static final String _S_MILLISECOND = "ms";
	public static final String _S_SECOND = "s";
	public static final String _S_MINUTE = "m";
	public static final String _S_HOUR = "h";
	public static final String _S_WEEK = "w";
	public static final String _S_DAY = "day";
	public static final String _S_MONTH = "month";
	public static final String _S_YEAR = "year";
	
	public static String epoch() {
		return Long.toString(System.currentTimeMillis());
	}// end of epoch
	
	public static String format(String pattern, Date date, TimeZone time_zone, Locale locale) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, locale);
		simpleDateFormat.setTimeZone(time_zone);
		return simpleDateFormat.format(date);
	}// end of format
	public static String format(String pattern, Date date, TimeZone time_zone) {
		return format(pattern, date, time_zone, Locale.getDefault());
	}// end of format
	public static String format(String pattern, Date date, String time_zone) {
		return format(pattern, date, TimeZone.getTimeZone(time_zone));
	}// end of format
	public static String format(String pattern, Date date) {
		return format(pattern, date, TimeZone.getDefault());
	}// end of format
	public static String format(String pattern) {
		return format(pattern, new Date());
	}// end of format
	public static String format(Date date) {
		return format("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", date, TimeZone.getDefault());
	}// end of format
	public static String format() {
		return format("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", new Date(), TimeZone.getDefault());
	}// end of format
	
	public static Date date(String date, String pattern, TimeZone time_zone, Locale locale) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, locale);
		simpleDateFormat.setTimeZone(time_zone);
		return simpleDateFormat.parse(date);
	}// end of date
	public static Date date(String date, String pattern, TimeZone time_zone) throws ParseException {
		return date(date, pattern, time_zone, Locale.getDefault());
	}// end of date
	public static Date date(String date, String pattern) throws ParseException {
		return date(date, pattern, TimeZone.getDefault(), Locale.getDefault());
	}// end of date
	
	public static Long diff(Date date_from, Date date_to) {
		return Math.abs(date_to.getTime() - date_from.getTime());
	}// end of diff
	
	public static Date add(Date date, String time_unit, int amount) {
		
		Date addedDate = null;
		
		switch (time_unit) {
			case _S_MILLISECOND:
				addedDate = DateUtils.addMilliseconds(date, amount);
				break;
			case _S_SECOND:
				addedDate = DateUtils.addSeconds(date, amount);
				break;
			case _S_MINUTE:
				addedDate = DateUtils.addMinutes(date, amount);
				break;
			case _S_HOUR:
				addedDate = DateUtils.addHours(date, amount);
				break;
			case _S_WEEK:
				addedDate = DateUtils.addWeeks(date, amount);
				break;
			case _S_DAY:
				addedDate = DateUtils.addDays(date, amount);
				break;
			case _S_MONTH:
				addedDate = DateUtils.addMonths(date, amount);
				break;
			case _S_YEAR:
				addedDate = DateUtils.addYears(date, amount);
				break;
			default:
				break;
		}
		
		return addedDate;
	}// end of add
	
}
