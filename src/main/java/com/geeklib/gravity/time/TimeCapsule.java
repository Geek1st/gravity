package com.geeklib.gravity.time;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 时光胶囊，提供时间日期类型的工具
 * 
 * @author Geek1st
 *
 */
public interface TimeCapsule
{
	/**
	 * 将日期类型转换成字符串形式提供的常见样式枚举
	 * @see SimpleDateFormat
	 * @author Geek1st
	 *
	 */
	public enum Style
	{
		yyyyMMdd, yyMMdd
	}

	/**
	 * 获取当前日期，统一提供{@link java.util.Date} 与其子类的入口。
	 * 使用时需要注意如下方式，需要指定具体内容来接收方法返回的对象。
	 * <pre>java.util.Date date = TimeCapsule.getDate(java.util.Date.class)</pre> 
	 * 
	 * @param clazz
	 *            需要获取日期类型的Class对象
	 * @return 指定日期类型的当前日期
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getDate(Class<? extends java.util.Date> clazz)
	{
		long millisecond = Calendar.getInstance().getTimeInMillis();

		if (java.util.Date.class == clazz)
		{
			return (T) new java.util.Date();
		}
		else if (java.sql.Date.class == clazz)
		{
			return (T) new java.sql.Date(millisecond);
		}
		else if (Timestamp.class == clazz)
		{
			return (T) new Timestamp(millisecond);
		}
		else if (java.sql.Time.class == clazz)
		{
			return (T) new java.sql.Time(millisecond);
		}

		return null;
	}

	/**
	 * 将日期类型转换成字符串形式
	 * 
	 * @param date
	 *            需要转换的日期
	 * @param style
	 *            根据指定的样式，参照{@link SimpleDateFormat}
	 * @return 转换后的日期字符串
	 */
	public static String convertDateToString(java.util.Date date, String style)
	{
		DateFormat dateFormat = new SimpleDateFormat(style);
		return dateFormat.format(date);
	}

	/**
	 * 将日期类型转换成字符串形式
	 * 
	 * @param date
	 *            需要转换的日期
	 * @param style
	 *            字符串表示的样式，枚举来自 {@link Style}
	 * @return 转换后的日期字符串
	 */
	public static String convertDateToString(java.util.Date date, Style style)
	{
		return convertDateToString(date, style.toString());
	}

	/**
	 * 将当前时间转换成字符串形式
	 * 
	 * @param style
	 *            字符串表示的样式，枚举来自 {@link Style}
	 * @return 转换后的日期字符串
	 */
	public static String convertCurrentDateToString(Style style)
	{
		Calendar cal = Calendar.getInstance();
		return convertDateToString(cal.getTime(), style);
	}
	
	/**
	 * 将时间转换成数值形式
	 * 
	 * @param style
	 *            数值表示的样式，枚举来自 {@link Style}
	 * @return 转换后的日期数值
	 */
	public static int convertDateToInt(java.util.Date date, Style style)
	{
		String str = convertDateToString(date, style);
		return Integer.parseInt(str);
	}
	
	/**
	 * 将时间转换成数值形式
	 * 
	 * @param style
	 *            根据指定的样式，参照{@link SimpleDateFormat}
	 * @return 转换后的日期数值
	 */
	public static int convertDateToInt(java.util.Date date, String style)
	{
		String str = convertDateToString(date, style);
		return Integer.parseInt(str);
	}
	
	/**
	 * 将当前时间转换成数值形式
	 * 
	 * @param style
	 *            数值表示的样式，枚举来自 {@link Style}
	 * @return 转换后的日期数值
	 */
	public static int convertCurrentDateToInt(Style style)
	{
		String str = convertCurrentDateToString(style);
		return Integer.parseInt(str);
	}
}
