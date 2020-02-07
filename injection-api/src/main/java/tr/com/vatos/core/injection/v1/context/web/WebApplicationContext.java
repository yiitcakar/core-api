package tr.com.vatos.core.injection.v1.context.web;

import tr.com.vatos.core.injection.v1.context.ApplicationContext;
import tr.com.vatos.core.injection.v1.context.ContextType;
import tr.com.vatos.core.injection.v1.context.impl.AbstractApplicationContextImpl;

import java.util.Map;

public interface WebApplicationContext extends ApplicationContext {
    public static class Impl extends AbstractApplicationContextImpl implements WebApplicationContext{
        protected Impl(ContextType contextType, Map<String, Object> beanMap) {
            super(contextType, beanMap);
        }
    }
}
