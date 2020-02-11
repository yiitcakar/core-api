package tr.com.vatos.core.dynamicproxy.builder;

import tr.com.vatos.core.builder.impl.line.NodeBuilder;
import tr.com.vatos.core.dynamicproxy.action.ProxyAction;

public class DynamicProxyActionSelectionBuilder<T> extends NodeBuilder<DynamicProxyBuilder.DynamicProxyBuilderContext<T>> {

    protected DynamicProxyActionSelectionBuilder(DynamicProxyBuilder.DynamicProxyBuilderContext<T> builderContext) {
        super(builderContext);
    }

    public DynamicProxyFinalizerBuilder<T> proxyAction(ProxyAction proxyAction)
    {
        getBuilderContext().setProxyAction(proxyAction);
        return new DynamicProxyFinalizerBuilder<T>(getBuilderContext());
    }

}
