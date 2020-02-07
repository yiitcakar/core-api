package tr.com.vatos.core.injection.v1.context.impl;

import tr.com.vatos.core.injection.v1.context.ApplicationContext;
import tr.com.vatos.core.injection.v1.context.ContextType;

import java.util.Collection;
import java.util.Map;

public abstract class AbstractApplicationContextImpl implements ApplicationContext {

    private final ContextType contextType;
    private final Map<String, Object> beanMap;

    protected AbstractApplicationContextImpl(ContextType contextType, Map<String, Object> beanMap) {
        this.contextType = contextType;
        this.beanMap = beanMap;
    }

    @Override
    public ContextType contextType() {
        return contextType;
    }

    @Override
    public <T> T getBean(Class<?> type) {
        if (this.beanMap.containsKey(type.getName())) {
            return (T) this.beanMap.get(type.getName());
        } else {
            //TODO throw bean not found exception!
            return (T) null;
        }
    }

    @Override
    public Collection<Object> getAllBeans() {
        return this.beanMap.values();
    }
}
