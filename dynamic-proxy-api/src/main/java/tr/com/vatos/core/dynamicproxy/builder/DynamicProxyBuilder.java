package tr.com.vatos.core.dynamicproxy.builder;

import tr.com.vatos.core.builder.impl.line.StartBuilder;
import tr.com.vatos.core.builder.types.BuilderContext;
import tr.com.vatos.core.dynamicproxy.action.ProxyAction;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;

public class DynamicProxyBuilder<T> extends StartBuilder<DynamicProxyBuilder.DynamicProxyBuilderContext<T>> {


    public static <T> DynamicProxyMethodSelectionBuilder<T> withClass(Class<T> realClass){
        return new DynamicProxyMethodSelectionBuilder<T>(new DynamicProxyBuilderContext<T>(realClass));
    }

    public static class DynamicProxyBuilderContext<T> implements BuilderContext
    {
        private static final String PROXY_CLASS_PREFIX = "Proxy";

        private final Class<T> realClass;
        private final Collection<Method> methods;
        private final String proxyClassName;
        private ProxyAction proxyAction;

        protected DynamicProxyBuilderContext(Class<T> realClass){
            this.realClass = realClass;
            this.methods = new HashSet<>();
            this.proxyClassName = realClass.getSimpleName() + PROXY_CLASS_PREFIX;
        }

        public Class<T> getRealClass() {
            return realClass;
        }

        public Collection<Method> getMethods() {
            return methods;
        }

        public String getProxyClassName() {
            return proxyClassName;
        }

        public ProxyAction getProxyAction() {
            return proxyAction;
        }

        public void setProxyAction(ProxyAction proxyAction) {
            this.proxyAction = proxyAction;
        }
    }
}
