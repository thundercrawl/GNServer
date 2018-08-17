package com.bigvision.web.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {

	private static String datepattern ="yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	private static SimpleDateFormat datefmt= new SimpleDateFormat(datepattern);
	
	public  static String getLocaldate(Date date)
	{
		return datefmt.format(date);
	}
	
	public static Date getDateByString(String date) throws ParseException
	{
		return datefmt.parse(date);
	}
	
}
