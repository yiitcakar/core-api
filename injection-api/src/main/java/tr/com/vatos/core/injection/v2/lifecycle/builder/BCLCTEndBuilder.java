package tr.com.vatos.core.injection.v2.lifecycle.builder;

import tr.com.vatos.core.builder.impl.line.EndBuilder;
import tr.com.vatos.core.injection.v2.lifecycle.BeanCreationLifeCycleTemplate;

public class BCLCTEndBuilder extends EndBuilder<BeanCreationLifeCycleTemplate, BCLCTBuilder.Context> {

    protected BCLCTEndBuilder(BCLCTBuilder.Context builderContext) {
        super(builderContext);
    }

    @Override
    public BeanCreationLifeCycleTemplate build() {
        return new BeanCreationLifeCycleTemplateImpl(getBuilderContext());
    }
}
