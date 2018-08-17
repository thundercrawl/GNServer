package com.bigvision.web.util.test;

import java.text.ParseException;
import java.util.Date;

import com.bigvision.web.util.DateTimeUtil;

public class DateTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(DateTimeUtil.getLocaldate(new Date()));
		
	
		try {
			System.out.println(DateTimeUtil.getDateByString("2018-00-12T18:57:25.553+0800"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
