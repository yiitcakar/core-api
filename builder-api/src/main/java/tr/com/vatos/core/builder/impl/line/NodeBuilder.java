package tr.com.vatos.core.builder.impl.line;

import tr.com.vatos.core.builder.behaviours.HasContext;
import tr.com.vatos.core.builder.types.Builder;
import tr.com.vatos.core.builder.types.BuilderContext;

public class NodeBuilder<C extends BuilderContext> implements Builder,HasContext<C> {

    protected final C builderContext;

    protected NodeBuilder(C builderContext){
        this.builderContext = builderContext;
    }

    protected C getBuilderContext(){
        return this.builderContext;
    }
}
