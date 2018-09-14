package com.bigvision.web.util;

import java.util.ArrayList;
import java.util.List;

public class Constants {

	public static String[] components  = {"fundefficient","kunpeng","dingsheng","zhide","bussstat"};
	
	
	public static final String KUN_PENG = "坤鹏业务";
	public static final String DING_SHENG = "鼎盛业务";
	public static boolean isComponents(String name)
	{
		boolean rt = false;
		for(String in:components)
			if(in.equals(name)) return true;
		return rt;
	}
	
	public static List<String> getBussstatCol()
	{
		List<String> cols = new ArrayList<String>();
		cols.add("融资租赁公司");
		cols.add("预计办理人数");
		cols.add("已办理人数");
		cols.add("待办理人数");
		cols.add("已融资总额");
		cols.add("剩余融资总额");
		cols.add("本月预计融资总额");
		return cols;
	}
	public static List<String> getFundEfficientCol()
	{
		List<String> cols = new ArrayList<String>();
		cols.add("资金创建日期");
		cols.add("资金来源方");
		cols.add("资金总额");
		cols.add("资金用途");
		cols.add("月可放款总额");
		cols.add("已放款总额");
		cols.add("当月剩余款项");
		return cols;
	}
	
	public static List<String> getKunpengDingshengCol()
	{
		List<String> cols = new ArrayList<String>();
		cols.add("业务创建日期");
		cols.add("城市");
		cols.add("公司");
		cols.add("车型");
		cols.add("车辆指导价");
		cols.add("车辆开票价");
		cols.add("保险");
		cols.add("融资额");
		cols.add("备案人数");
		cols.add("过审人数");
		cols.add("下单时间");
		cols.add("完成时间");
		cols.add("完成人数");
		cols.add("总融资额");
		cols.add("资金用途");
		return cols;
	}
	
	public static List<String> getPermissionList()
	{
		List<String> list = new ArrayList<String>();
		list.add("添加删除成员");
		list.add("编辑信息");
		list.add("查看信息");
		list.add("导出Excel");
		list.add("导入Excel");
		return list;
	}
	public static List<String> getPermissionByName(String name)
	{
		List<String> list = new ArrayList<String>();
		if(name.equals("添加删除成员"))
		{
			list.add("1011");
			list.add("1012");
			list.add("1013");
		}
		if(name.equals("编辑信息"))
		{
			list.add("3012");
			list.add("3022");
			list.add("3032");
			list.add("3042");
			list.add("3052");
		}
		
		if(name.equals("查看信息"))
		{
			list.add("3011");
			list.add("3021");
			list.add("3031");
			list.add("3041");
			list.add("3051");
		}
		if(name.equals("导入Excel"))
		{
			list.add("3013");
			list.add("3023");
			list.add("3033");
			list.add("3043");
			list.add("3053");
		}
		
		if(name.equals("导出Excel"))
		{
			list.add("3014");
			list.add("3024");
			list.add("3034");
			list.add("3044");
			list.add("3054");
		}
		
		return list;
	}
	/*
	 * <el-checkbox label="添加删除成员"></el-checkbox>
                    <el-checkbox label="编辑信息"></el-checkbox>
                    <el-checkbox label="查看信息"></el-checkbox>
                    <el-checkbox label="导入导出Excel"></el-checkbox>
	 */
	public  static List<String> convertRoleToResourceID(List<String> permissions)
	{
		ArrayList<String> rtList = new ArrayList<String>();
		
		for(String p :permissions)
		{
			List<String> plist = getPermissionByName(p);
			for(String in:plist)
				rtList.add(in);
		}
		
		return rtList;
	}
}
