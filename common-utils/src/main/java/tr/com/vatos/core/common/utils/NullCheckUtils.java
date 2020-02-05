package tr.com.vatos.core.common.utils;

import java.util.Collection;

public class NullCheckUtils 
{
	public static boolean isObjectEmpty(Object o) {
		return o == null;
	}
	
	public static boolean isNotObjectEmpty(Object o) {
		return o != null;
	}
	
	public static boolean isArrayEmpty(Object[] arr) {
		return isObjectEmpty(arr) ? true : arr.length == 0;
	}
	
	public static boolean isNotArrayEmpty(Object[] arr) {
		return isNotObjectEmpty(arr) ? arr.length > 0 : false;
	}
	
	public static boolean isCollectionEmpty(Collection<?> c) {
		return isObjectEmpty(c) ? true : c.size() == 0;
	}
	
	public static boolean isNotCollectionEmpty(Collection<?> c) {
		return isNotObjectEmpty(c) ? c.size() > 0 : false ;
	}
}
