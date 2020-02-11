package tr.com.vatos.core.builder.impl;

import tr.com.vatos.core.builder.behaviours.BuildInstance;
import tr.com.vatos.core.builder.types.Builder;

public abstract class SimpleBuilder<I> implements Builder, BuildInstance<I>
{
    protected I instance;


}
