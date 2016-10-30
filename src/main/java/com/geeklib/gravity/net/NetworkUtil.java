package com.geeklib.gravity.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class NetworkUtil
{
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
}
