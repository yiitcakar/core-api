package tr.com.vatos.core.injection.v2.lifecycle.builder;

import tr.com.vatos.core.builder.impl.line.NodeBuilder;

public class BCLCTOrganizerEndBuilder extends NodeBuilder<BCLCTBuilder.Context> {

    protected BCLCTOrganizerEndBuilder(BCLCTBuilder.Context builderContext) {
        super(builderContext);
    }

    public BCLCTCreatorBuilder creators(){
        return new BCLCTCreatorBuilder(getBuilderContext());
    }

}
