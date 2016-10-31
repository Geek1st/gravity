package com.geeklib.gravity.string;

import java.text.DecimalFormat;

public class StringUtil
{
	/**
	 * 判断一组字符串是否为空（引用/值）
	 * 
	 * @param args
	 *            字符串数组
	 * @return -true：不为空 -false：为空
	 */
	public static boolean isNull(String... args)
	{
		for (String arg : args)
		{
			if (null != arg && 0 != arg.length())
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * 判断一个字符串是否为空（引用/值）
	 * 
	 * @param args
	 *            字符串数组
	 * @return -true：不为空 -false：为空
	 */
	public static boolean isNull(String arg)
	{
		return isNull(new String[] { arg });
	}

	/**
	 * 转换成中国汉字大写
	 * 
	 * @param o
	 *            需要被转换的数值
	 * @return 大写汉字字符串
	 */
	public static String toChineseCurrency(Number o)
	{
		String s = new DecimalFormat("#.00").format(o);
		s = s.replaceAll("\\.", "");
		char[] digit = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' };
		String unit = "仟佰拾兆仟佰拾亿仟佰拾万仟佰拾元角分";
		int l = unit.length();
		StringBuffer sb = new StringBuffer(unit);
		for (int i = s.length() - 1; i >= 0; i--)
			sb = sb.insert(l - s.length() + i, digit[(s.charAt(i) - 0x30)]);
		s = sb.substring(l - s.length(), l + s.length());
		s = s.replaceAll("零[拾佰仟]", "零").replaceAll("零{2,}", "零").replaceAll("零([兆万元])", "$1").replaceAll("零[角分]", "");
		if (s.endsWith("角"))
			s += "零分";
		if (!s.contains("角") && !s.contains("分") && s.contains("元"))
			s += "整";
		if (s.contains("分") && !s.contains("整") && !s.contains("角"))
			s = s.replace("元", "元零");
		return s;
	}

	/**
	 * 转义单引号的方法。 当SQL中包含有单引号的参数时会执行失败，使用该方法进行单引号转义
	 * 
	 * @param character
	 *            待转义的字符串
	 * @return 转义后的字符串
	 */
	public static String espace(String character)
	{
		if (0 != character.indexOf("\'"))
		{
			return character.replace("\'", "\'\'");
		}
		return character;
	}
}
