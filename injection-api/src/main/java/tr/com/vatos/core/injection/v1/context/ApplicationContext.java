package tr.com.vatos.core.injection.v1.context;

import java.util.Collection;

public interface ApplicationContext
{
	ContextType contextType();
	<T> T getBean(Class<?> type);
	Collection<Object> getAllBeans();
}
