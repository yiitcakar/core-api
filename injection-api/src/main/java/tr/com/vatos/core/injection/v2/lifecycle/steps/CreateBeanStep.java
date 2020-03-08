package tr.com.vatos.core.injection.v2.lifecycle.steps;

import tr.com.vatos.core.injection.v2.meta.BeanMetaData;
import tr.com.vatos.core.injection.v2.meta.BeanSourceType;

import java.util.Map;

public interface CreateBeanStep {
    Object createBean(BeanMetaData beanMetaData, Map<String,Object> beanMap);

    BeanSourceType getSourceType();
}
