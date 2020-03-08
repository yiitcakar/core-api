package tr.com.vatos.core.injection.v2.lifecycle.builder;

import tr.com.vatos.core.builder.impl.line.NodeBuilder;
import tr.com.vatos.core.injection.v2.lifecycle.steps.OrganizeBeanMetaDataStep;

public class BCLCTOrganizerBuilder extends NodeBuilder<BCLCTBuilder.Context> {

    protected BCLCTOrganizerBuilder(BCLCTBuilder.Context builderContext) {
        super(builderContext);
    }

    public BCLCTOrganizerEndBuilder organizer(OrganizeBeanMetaDataStep step){
        getBuilderContext().setOrganizeBeanMetaDataStep(step);
        return new BCLCTOrganizerEndBuilder(getBuilderContext());
    }

}
