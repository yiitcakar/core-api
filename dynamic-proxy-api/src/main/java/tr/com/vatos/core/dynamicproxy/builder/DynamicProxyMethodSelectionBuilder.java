package tr.com.vatos.core.dynamicproxy.builder;

import tr.com.vatos.core.builder.impl.line.NodeBuilder;
import tr.com.vatos.core.reflections.classes.ClassManager;

import java.lang.annotation.Annotation;
import java.util.Arrays;

public class DynamicProxyMethodSelectionBuilder<T> extends NodeBuilder<DynamicProxyBuilder.DynamicProxyBuilderContext<T>> {


    protected DynamicProxyMethodSelectionBuilder(DynamicProxyBuilder.DynamicProxyBuilderContext<T> builderContext) {
        super(builderContext);
    }

    public DynamicProxyActionSelectionBuilder<T> allMethods(){
        getBuilderContext()
                .getMethods()
                .addAll(Arrays.asList(getBuilderContext()
                        .getRealClass()
                        .getDeclaredMethods()));
        return new DynamicProxyActionSelectionBuilder(getBuilderContext());
    }

    public DynamicProxyActionSelectionBuilder<T> methodsWithAnnotaion(Class<? extends Annotation> annotation)
    {
        getBuilderContext()
                .getMethods()
                .addAll(ClassManager.instance()
                        .getMethodsFromClassWithGivenAnnotation(getBuilderContext().getRealClass(),annotation));
        return new DynamicProxyActionSelectionBuilder(getBuilderContext());
    }
}
