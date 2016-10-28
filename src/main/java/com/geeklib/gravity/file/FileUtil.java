package com.geeklib.gravity.file;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.io.File;

public class FileUtil
{
	/**
	 * 获取File对象所指向文件的MD5
	 * 
	 * @param file
	 *            待获取MD5的文件
	 * @return 文件的MD5信息
	 */
	public static String getMD5(File file)
	{
		return getID(file, "MD5");
	}


	/**
	 * 获取File对象所指向文件的SHA1
	 * 
	 * @param file
	 *            待获取SHA1的文件
	 * @return 文件的SHA1信息
	 */
	public static String getSHA1(File file)
	{
		return getID(file, "SHA-1");
	}

	/**
	 * 获取File对象所指向文件的唯一信息
	 * 
	 * @param file
	 *            待获取的文件
	 * @param algorithm
	 *            算法
	 * @return 通过指定算法生成的文件唯一标识
	 */
	private static String getID(File file, String algorithm)
	{
		if (null == file)
		{
			return null;
		}

		MessageDigest messageDigest = null;
		BufferedInputStream bufferedInputStream = null;

		byte[] buffer = new byte[4096];
		int len;

		try
		{
			messageDigest = MessageDigest.getInstance(algorithm);
			bufferedInputStream = new BufferedInputStream(new FileInputStream(file));

			while ((len = bufferedInputStream.read(buffer)) != -1)
			{
				messageDigest.update(buffer, 0, len);
			}

			BigInteger bigInt = new BigInteger(1, messageDigest.digest());

			return bigInt.toString(16);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
			try
			{
				bufferedInputStream.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
