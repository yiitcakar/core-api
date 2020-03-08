package tr.com.vatos.core.injection.v2.lifecycle.steps;

import tr.com.vatos.core.injection.v2.meta.BeanMetaData;

import java.util.Set;

public interface ValidateBeanMetaDataStep
{
    boolean validateBeanMetaData(Set<BeanMetaData> allBeanMetaData);


}
