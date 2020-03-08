package tr.com.vatos.core.injection.v2.lifecycle;

import tr.com.vatos.core.injection.v2.lifecycle.builder.BCLCTBuilder;
import tr.com.vatos.core.injection.v2.meta.BeanMetaDataSource;

import java.util.Map;
import java.util.Set;

public interface BeanCreationLifeCycleTemplate {

    static BCLCTBuilder builder(){return new BCLCTBuilder();}

    Map<String, Object> startBeanCreationLifeCycle(Set<BeanMetaDataSource> metaDataSource);
}
