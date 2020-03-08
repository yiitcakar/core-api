package tr.com.vatos.core.injection.v2.lifecycle.steps;

import tr.com.vatos.core.injection.v2.meta.BeanMetaData;

import java.util.Map;
import java.util.Set;

public interface OrganizeBeanMetaDataStep {
    Map<Integer,Set<BeanMetaData>> organizeBeanMetaData(Set<BeanMetaData> allBeanMetaData);
}
