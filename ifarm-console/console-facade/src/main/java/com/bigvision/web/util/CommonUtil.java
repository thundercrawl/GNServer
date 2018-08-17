package com.bigvision.web.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {
	private static String datepattern ="yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	private static SimpleDateFormat datefmt= new SimpleDateFormat(datepattern);
	public static String  getLoggerTime()
	{
		String dstr = "";
		dstr = datefmt.format(new Date());
		return dstr;
	}
}
