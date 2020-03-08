package tr.com.vatos.core.injection.v2.lifecycle.builder;

import tr.com.vatos.core.builder.impl.line.NodeBuilder;
import tr.com.vatos.core.injection.v2.lifecycle.steps.ReadBeanMetaDataStep;
import tr.com.vatos.core.injection.v2.meta.BeanMetaDataSource;
import tr.com.vatos.core.injection.v2.meta.BeanSourceType;

public class BCLCTReaderBuilder extends NodeBuilder<BCLCTBuilder.Context> {

    protected BCLCTReaderBuilder(BCLCTBuilder.Context builderContext) {
        super(builderContext);
    }

    public <S extends BeanMetaDataSource> BCLCTReaderBuilder addReaderStep(ReadBeanMetaDataStep<S> step){
        getBuilderContext().addReadBeanMetaDataStepMap(step.getSourceType(),step);
        return this;
    }

    public BCLCTCombinerBuilder end(){
        return new BCLCTCombinerBuilder(getBuilderContext());
    }

}
