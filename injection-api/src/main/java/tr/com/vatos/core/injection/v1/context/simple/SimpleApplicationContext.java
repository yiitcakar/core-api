package tr.com.vatos.core.injection.v1.context.simple;

import tr.com.vatos.core.injection.v1.context.ApplicationContext;
import tr.com.vatos.core.injection.v1.context.ContextType;
import tr.com.vatos.core.injection.v1.context.impl.AbstractApplicationContextImpl;

import java.util.Map;

public interface SimpleApplicationContext extends ApplicationContext {
    public static abstract class Impl extends AbstractApplicationContextImpl implements SimpleApplicationContext{
        protected Impl(ContextType contextType, Map<String, Object> beanMap) {
            super(contextType, beanMap);
        }
    }
}
