package tr.com.vatos.core.injection.v1.application.builder;

import java.util.HashSet;
import java.util.Set;

import tr.com.vatos.core.common.utils.NullCheckUtils;
import tr.com.vatos.core.injection.v1.annotations.Configuration;
import tr.com.vatos.core.injection.v1.application.Application;
import tr.com.vatos.core.reflections.classes.ClassManager;

public class ApplicationBuilder {
	
	private final ClassManager classManager;
	private final Set<Class<?>> configurations;
	
	private ApplicationBuilder() {
		this.configurations = new HashSet<>();
		this.classManager = ClassManager.instance();
	}
	
	public static ApplicationBuilder newInstance() {return new ApplicationBuilder();}
	
	public ApplicationBuilder addConfiguration(Class<?> cls) 
	{
		addConfigurationClass(cls);
		return this;
	}
	
	private void addConfigurationClass(Class<?> cls) 
	{
		if(classManager.classHasAnnotation(cls, Configuration.class)) 
		{
			this.configurations.add(cls);
			Configuration configuration = classManager.getAnnotationFromClassWithoutNullCheck(cls, Configuration.class);
			if(NullCheckUtils.isNotArrayEmpty(configuration.imports()))
			{
				for(Class<?> imported : configuration.imports())
				{
					addConfiguration(imported);
				}
			}
		}
		else
		{
			//TODO throw exception!
			System.out.println("not added " + cls.getSimpleName());
		}
	}
	
	public ApplicationBuilder status()
	{
		configurations.stream().forEach(System.out::println);
		return this;
	}
	
	public Application build() 
	{
		return null;
	}
}
