package tr.com.vatos.core.builder.impl;

import tr.com.vatos.core.builder.behaviours.HasContext;
import tr.com.vatos.core.builder.behaviours.ReturnParent;
import tr.com.vatos.core.builder.types.Builder;
import tr.com.vatos.core.builder.types.BuilderContext;

public abstract class OuterChildBuilder<P extends Builder,C extends BuilderContext> extends ChildBuilder<P> implements HasContext<C> , ReturnParent<P> {

    private final C context;

    protected OuterChildBuilder(P parent,C context) {
        super(parent);
        this.context =  context;
    }

    protected C getContext(){
        return this.context;
    }

    protected void beforeEnd(){}

    @Override
    public final P end() {
        beforeEnd();
        return super.getParent();
    }
}
