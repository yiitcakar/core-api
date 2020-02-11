package tr.com.vatos.core.builder.impl.tree;

import tr.com.vatos.core.builder.behaviours.HasContext;
import tr.com.vatos.core.builder.types.Builder;
import tr.com.vatos.core.builder.types.BuilderContext;

public abstract class InnerChildBuilder<P extends Builder,C extends BuilderContext> extends ChildBuilder<P> implements HasContext<C> {

    protected final C context;

    protected InnerChildBuilder(P parent, C context) {
        super(parent);
        this.context = context;
    }

    protected C getContext(){
        return this.context;
    }
}
