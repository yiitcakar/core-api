package tr.com.vatos.core.injection.v2.lifecycle.builder;

import tr.com.vatos.core.builder.impl.line.NodeBuilder;
import tr.com.vatos.core.injection.v2.lifecycle.steps.CombineBeanMetaDataStep;
import tr.com.vatos.core.injection.v2.lifecycle.steps.ReadBeanMetaDataStep;
import tr.com.vatos.core.injection.v2.meta.BeanMetaDataSource;
import tr.com.vatos.core.injection.v2.meta.BeanSourceType;

public class BCLCTCombinerBuilder extends NodeBuilder<BCLCTBuilder.Context> {

    protected BCLCTCombinerBuilder(BCLCTBuilder.Context builderContext) {
        super(builderContext);
    }

    public BCLCTCombinerEndBuilder combiner(CombineBeanMetaDataStep step){
        getBuilderContext().setCombineBeanMetaDataStep(step);
        return new BCLCTCombinerEndBuilder(getBuilderContext());
    }

}
