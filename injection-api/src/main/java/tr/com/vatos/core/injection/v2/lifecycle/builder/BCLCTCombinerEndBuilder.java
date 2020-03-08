package tr.com.vatos.core.injection.v2.lifecycle.builder;

import tr.com.vatos.core.builder.impl.line.NodeBuilder;
import tr.com.vatos.core.injection.v2.lifecycle.steps.CombineBeanMetaDataStep;

public class BCLCTCombinerEndBuilder extends NodeBuilder<BCLCTBuilder.Context> {

    protected BCLCTCombinerEndBuilder(BCLCTBuilder.Context builderContext) {
        super(builderContext);
    }

    public BCLCTValidatorBuilder validators(){
        return new BCLCTValidatorBuilder(getBuilderContext());
    }

}
