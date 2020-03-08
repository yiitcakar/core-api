package tr.com.vatos.core.injection.v2.lifecycle.builder;

import tr.com.vatos.core.builder.impl.line.NodeBuilder;
import tr.com.vatos.core.injection.v2.lifecycle.steps.CreateBeanStep;
import tr.com.vatos.core.injection.v2.meta.BeanMetaDataSource;
import tr.com.vatos.core.injection.v2.meta.BeanSourceType;

public class BCLCTCreatorBuilder extends NodeBuilder<BCLCTBuilder.Context> {

    protected BCLCTCreatorBuilder(BCLCTBuilder.Context builderContext) {
        super(builderContext);
    }

    public <S extends BeanMetaDataSource> BCLCTCreatorBuilder addBeanCreatorStep(CreateBeanStep step){
        getBuilderContext().addCreateBeanStepMap(step.getSourceType(),step);
        return this;
    }

    public BCLCTEndBuilder end(){
        return new BCLCTEndBuilder(getBuilderContext());
    }

}
