package tr.com.vatos.core.injection.v1.context.web.builder;

import tr.com.vatos.core.injection.v1.application.builder.ApplicationBuilder;
import tr.com.vatos.core.injection.v1.constants.enums.context.DefaultContextTypesEnum;
import tr.com.vatos.core.injection.v1.context.ApplicationContext;
import tr.com.vatos.core.injection.v1.context.builder.AbstractApplicationContextBuilder;
import tr.com.vatos.core.injection.v1.context.web.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class WebApplicationContextBuilder extends AbstractApplicationContextBuilder<WebApplicationContextBuilder> {

    public WebApplicationContextBuilder(ApplicationBuilder parent, ApplicationBuilder.ApplicationBuildingContext context) {
        super(parent, context, DefaultContextTypesEnum.WEB);
    }

    @Override
    protected ApplicationContext buildApplicationContext(Map<String,Object> beanMap) {
        return new WebApplicationContext.Impl(super.contextType,beanMap) {};
    }
}
