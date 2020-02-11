package tr.com.vatos.core.builder.impl.tree;

import tr.com.vatos.core.builder.behaviours.HasParent;
import tr.com.vatos.core.builder.types.Builder;

public abstract class ChildBuilder<P extends Builder> implements Builder, HasParent<P>
{
    private final P parent;

    protected ChildBuilder(P parent){
        this.parent = parent;
    }

    protected P getParent(){
        return this.parent;
    }
}
