package com.geeklib.gravity.mirror;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * 影子工厂
 * @author Geek1st
 *
 */
public interface ShadowFactory
{

	/**
	 * 深度克隆一个“完整”的对象，克隆出的对象内容完全和原有对象相同，如果对象中含有引用类型的变量，那么也会去克隆该变量。
	 * 该方法可能会导致序列化异常，参照{@link Serializable}，如果需要浅克隆，参照{@link Object}的clone()方法。
	 * @param t 需要被克隆的对象
	 * @return 克隆生成的对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T clone(T t)
	{
		if(null == t)
		{
			return null;
		}
		
		T tCopy = null;
		
		try
		{
			OutputStream outputStream = new FileOutputStream("tmp");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(t);
			objectOutputStream.close();
			
			InputStream inputStream = new FileInputStream("tmp");
			ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
			tCopy = (T)objectInputStream.readObject();
			objectInputStream.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return tCopy; 
	}
}
