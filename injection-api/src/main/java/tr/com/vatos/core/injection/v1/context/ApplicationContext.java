package tr.com.vatos.core.injection.v1.context;

public interface ApplicationContext 
{
	<T> T getBean(Class<?> type);
}
