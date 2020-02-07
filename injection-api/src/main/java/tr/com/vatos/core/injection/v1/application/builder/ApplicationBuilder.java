package tr.com.vatos.core.injection.v1.application.builder;

import tr.com.vatos.core.builder.impl.SimpleBuilder;
import tr.com.vatos.core.builder.types.BuilderContext;
import tr.com.vatos.core.injection.v1.application.Application;
import tr.com.vatos.core.injection.v1.constants.enums.context.DefaultContextTypesEnum;
import tr.com.vatos.core.injection.v1.context.ApplicationContext;
import tr.com.vatos.core.injection.v1.context.ContextType;
import tr.com.vatos.core.injection.v1.context.simple.builder.SimpleApplicationContextBuilder;
import tr.com.vatos.core.injection.v1.context.web.builder.WebApplicationContextBuilder;

import java.util.HashMap;
import java.util.Map;

public class ApplicationBuilder extends SimpleBuilder<Application> {
	
	private final ApplicationBuildingContext applicationBuildingContext;

	private ApplicationBuilder() {
		this.applicationBuildingContext = new ApplicationBuildingContext();
	}
	
	public static ApplicationBuilder newInstance() {return new ApplicationBuilder();}

	public WebApplicationContextBuilder webContext(){
		return new WebApplicationContextBuilder(this,this.applicationBuildingContext);
	}

	public SimpleApplicationContextBuilder simpleContext(){
		return new SimpleApplicationContextBuilder(this,this.applicationBuildingContext);
	}

	public ApplicationBuilder status()
	{
		this.applicationBuildingContext
				.contextMap
				.entrySet()
				.forEach(entry->
				{
					System.out.println(entry.getKey().toString());
					entry.getValue().getAllBeans().forEach(System.out::println);
				});
		return this;
	}


	@Override
	public Application build() {
		return null;
	}

	public static class ApplicationBuildingContext implements BuilderContext
	{
		private final Map<ContextType, ApplicationContext> contextMap = new HashMap<ContextType, ApplicationContext>();

		public void put(ContextType type,ApplicationContext applicationContext){
			this.contextMap.put(type,applicationContext);
		}

		public ApplicationContext getContext(ContextType type){
			return this.contextMap.get(type);
		}
	}
}
