package tr.com.vatos.core.injection.v1.context.simple.builder;

import tr.com.vatos.core.injection.v1.application.builder.ApplicationBuilder;
import tr.com.vatos.core.injection.v1.constants.enums.context.DefaultContextTypesEnum;
import tr.com.vatos.core.injection.v1.context.ApplicationContext;
import tr.com.vatos.core.injection.v1.context.builder.AbstractApplicationContextBuilder;
import tr.com.vatos.core.injection.v1.context.simple.SimpleApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class SimpleApplicationContextBuilder extends AbstractApplicationContextBuilder<SimpleApplicationContextBuilder> {

    public SimpleApplicationContextBuilder(ApplicationBuilder parent, ApplicationBuilder.ApplicationBuildingContext context) {
        super(parent, context, DefaultContextTypesEnum.SIMPLE);
    }

    @Override
    protected ApplicationContext buildApplicationContext() {
        Map<String,Object> beanMap = new HashMap<>();
        return new SimpleApplicationContext.Impl(super.contextType,beanMap){};
    }




}
