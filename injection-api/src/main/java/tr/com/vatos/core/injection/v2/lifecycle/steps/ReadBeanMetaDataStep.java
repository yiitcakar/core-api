package tr.com.vatos.core.injection.v2.lifecycle.steps;

import tr.com.vatos.core.injection.v2.meta.BeanMetaData;
import tr.com.vatos.core.injection.v2.meta.BeanMetaDataSource;
import tr.com.vatos.core.injection.v2.meta.BeanSourceType;

import java.util.Set;

public interface ReadBeanMetaDataStep<S extends BeanMetaDataSource>
{
     Set<BeanMetaData<S>> readBeanMetaData(S source);

     BeanSourceType getSourceType();
}
