package tr.com.vatos.core.injection.v1.context;

public interface ApplicationContext
{
	ContextType contextType();
	<T> T getBean(Class<?> type);
}
