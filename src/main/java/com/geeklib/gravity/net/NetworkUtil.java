package com.geeklib.gravity.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.geeklib.gravity.string.StringUtil;

/**
 * 网络工具类
 * 
 * @author Geek1st
 *
 */
public class NetworkUtil
{
	/**
	 * 广播地址
	 */
	static InetAddress broadcastAddress = null;

	static
	{
		try
		{
			InetAddress.getByName("255.255.255.255");
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 向局域网发送广播
	 * 
	 * @param message
	 *            需要被广播的数据
	 * @param port
	 *            端口号
	 */
	public static void broadcast(String message, int port)
	{
		DatagramSocket socket = null;

		try
		{
			socket = new DatagramSocket();
		}
		catch (SocketException e)
		{
			e.printStackTrace();
		}
		DatagramPacket packet = new DatagramPacket(message.getBytes(), 0, message.length(), broadcastAddress, port);

		try
		{
			socket.send(packet);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 判断目标主机端口是否被使用
	 * @param address 目标主机的地址
	 * @param port 端口号
	 * @return 如果目标主机端口被占用（正在监听使用，返回true，否则返回false）
	 */
	public static boolean isPortUsed(String address, int port)
	{
		Socket socket = null;
		try
		{
			socket = new Socket(address, port);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return socket.isConnected();
	}
	
	/**
	 * 判断一个字符串是否是标准IPv4地址
	 * @param address ip地址
	 * @return
	 */
	public static boolean isIPv4(String address)
	{
		if(StringUtil.isNull(address))
		{
			return false;
		}
		
		String[] nums = address.split(".");
		
		if(4 != nums.length)
		{
			return false;
		}
		
		for (String string : nums)
		{
			int num = Integer.parseInt(string);
			if(0 > num || 255 < num)
			{
				return false;
			}
		}
		
		return true;
	}
}
