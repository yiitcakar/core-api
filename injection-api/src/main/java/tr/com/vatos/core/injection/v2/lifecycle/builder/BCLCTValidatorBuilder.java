package tr.com.vatos.core.injection.v2.lifecycle.builder;

import tr.com.vatos.core.builder.impl.line.NodeBuilder;
import tr.com.vatos.core.injection.v2.lifecycle.steps.ValidateBeanMetaDataStep;
import tr.com.vatos.core.injection.v2.meta.BeanMetaDataSource;

public class BCLCTValidatorBuilder extends NodeBuilder<BCLCTBuilder.Context> {

    protected BCLCTValidatorBuilder(BCLCTBuilder.Context builderContext) {
        super(builderContext);
    }

    public <S extends BeanMetaDataSource> BCLCTValidatorBuilder addValidatorStep(ValidateBeanMetaDataStep step){
        getBuilderContext().addValidateBeanMetaDataStepSet(step);
        return this;
    }

    public BCLCTOrganizerBuilder end(){
        return new BCLCTOrganizerBuilder(getBuilderContext());
    }



}
