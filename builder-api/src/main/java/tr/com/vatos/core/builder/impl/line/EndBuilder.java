package tr.com.vatos.core.builder.impl.line;

import tr.com.vatos.core.builder.behaviours.BuildInstance;
import tr.com.vatos.core.builder.types.BuilderContext;

public abstract class EndBuilder<I,C extends BuilderContext> extends NodeBuilder<C> implements BuildInstance<I> {

    protected EndBuilder(C builderContext) {
        super(builderContext);
    }
}

