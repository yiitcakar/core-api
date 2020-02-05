package tr.com.vatos.core.reflections.packages;

import java.lang.annotation.Annotation;
import java.util.Collection;

public interface PackageManager 
{
	public static PackageManager instance() { return PackageManagerImpl.instance; }
	
	Collection<Class<?>> getAllClasses(String... packages);
	Collection<Class<?>> getAllClassesWithAnnotation(Class<? extends Annotation> annotation, String... packages);
	<T> Collection<Class<? extends T>> getAllClassesExtendsClass(Class<T> subType,String... packages);
	<T> Collection<Class<? extends T>> getAllClassesImplementsInterface(Class<T> subType,String... packages);
	
	Collection<Class<?>> getAllInterfaces(String... packages);
	
	Collection<Class<?>> getAllEnums(String... packages);
	
	Collection<Class<?>> getAllAnnotations(String... packages);
	
}
