package game.misc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.json.JSONObject;

import game.tile.Tile;

public class ClassUtils {
	public static Constructor<?> getConstructor(String className,Class<?>[] params){
		Constructor<?> tC = null;
		try {
			tC = Class.forName(className).getConstructor(params);
		}catch(Exception e){
			System.err.println(e);
		}
		return tC;
	}
	public static Constructor<?> getConstructor(Class<?> cls,Class<?>[] params){
		Constructor<?> tC = null;

			try {
				tC = cls.getConstructor(params);
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		return tC;
	}
	public static Object newInstance(String className,Class<?>[] params,Object[] data){
		try {
			return getConstructor(className, params).newInstance(data);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Object newInstance(Class cls,Class<?>[] params,Object[] data){
		try {
			return getConstructor(cls, params).newInstance(data);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Object newInstance(Class cls,Object[] data){
		try {
			return getConstructor(cls, getParams(data)).newInstance(data);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Object newInstance(String className,Object[] data){
		try {
			System.out.println(className + " " + getParams(data) + " " + data);
			return getConstructor(className, getParams(data)).newInstance(data);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Class<?>[] getParams(Object[] data){
		Class<?>[] params = new Class<?>[data.length];
		for(int i=0; i<data.length;i++ ){
			params[i] = data[i].getClass();
		}
		return params;
	}
	
	
}
