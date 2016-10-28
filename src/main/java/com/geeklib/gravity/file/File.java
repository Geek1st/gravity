package com.geeklib.gravity.file;

import java.net.URI;

/**
 * File类是java.io.File的一个子类，并且是个阻塞的类，相比于JDK提供的默认File，
 * 该File可以在对象初始化的时候自动记录所指向文件的MD5信息。
 * 但是请注意，在所指向的文件内容发生改变时，MD5是不会发生变化的，因为他是一个常量，也是符合常理的。
 * 除非你再去生成一个新的对象，该类的设计目的就在于通过生成新的对象，和之前的对象做对比，来判断文件内容是否发生变化，这种比较方式是十分精准的。
 * 
 * @author Geek1st
 *
 */
public class File extends java.io.File
{
	private final String MD5;

	public File(java.io.File parent, String child)
	{
		super(parent, child);
		MD5 = FileUtil.getMD5(this);
	}

	public File(String parent, String child)
	{
		super(parent, child);
		MD5 = FileUtil.getMD5(this);
	}

	public File(String pathname)
	{
		super(pathname);
		MD5 = FileUtil.getMD5(this);
	}

	public File(URI uri)
	{
		super(uri);
		MD5 = FileUtil.getMD5(this);
	}

	public String getMD5()
	{
		return MD5;
	}
	
	public boolean equals(File file)
	{
		return this.getMD5().equals(file.getMD5());
	}
}
