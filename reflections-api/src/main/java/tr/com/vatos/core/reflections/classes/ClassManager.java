package tr.com.vatos.core.reflections.classes;

import java.lang.annotation.Annotation;

public interface ClassManager 
{
	public static ClassManager instance() {return ClassManagerImpl.instance;}
	public static ClassManager newInstance() {return new ClassManagerImpl();}
	
	boolean classHasAnnotation(Class<?> cls,Class<? extends Annotation> ant);
	<T extends Annotation> T getAnnotationFromClassWithNullCheck(Class<?> cls,Class<T> ant);
	<T extends Annotation> T getAnnotationFromClassWithoutNullCheck(Class<?> cls,Class<T> ant);
}
