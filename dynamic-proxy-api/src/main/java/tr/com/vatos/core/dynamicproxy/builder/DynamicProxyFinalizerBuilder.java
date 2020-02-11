package tr.com.vatos.core.dynamicproxy.builder;

import tr.com.vatos.core.builder.impl.line.EndBuilder;
import tr.com.vatos.core.dynamicproxy.action.ProxyAction;
import tr.com.vatos.core.dynamicproxy.compiler.InMemoryJavaCompiler;
import tr.com.vatos.core.dynamicproxy.template.ProxyObjectTemplate;

public class DynamicProxyFinalizerBuilder<T> extends EndBuilder<T,DynamicProxyBuilder.DynamicProxyBuilderContext<T>> {

    protected DynamicProxyFinalizerBuilder(DynamicProxyBuilder.DynamicProxyBuilderContext<T> builderContext) {
        super(builderContext);
    }

    @Override
    public T build() {
        String proxyClassStr = ProxyObjectTemplate.getProxyObjectStr(
                getBuilderContext().getRealClass(),
                getBuilderContext().getMethods(),
                getBuilderContext().getProxyClassName());
        System.out.println(proxyClassStr);
        Class<?> proxyClass = InMemoryJavaCompiler.newInstance().compile(getBuilderContext().getProxyClassName(), proxyClassStr);
        T result = null;
        try {
            result =  (T) proxyClass.getConstructor(ProxyAction.class).newInstance(getBuilderContext().getProxyAction());
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
}
