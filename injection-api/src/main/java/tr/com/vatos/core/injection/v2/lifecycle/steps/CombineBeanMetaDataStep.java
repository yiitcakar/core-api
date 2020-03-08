package tr.com.vatos.core.injection.v2.lifecycle.steps;

import tr.com.vatos.core.injection.v2.meta.BeanMetaData;

import java.util.Set;

public interface CombineBeanMetaDataStep
{
    void combine(Set<BeanMetaData> root, Set<BeanMetaData> source);
}
