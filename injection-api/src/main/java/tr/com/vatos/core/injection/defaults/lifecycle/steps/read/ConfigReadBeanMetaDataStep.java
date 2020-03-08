package tr.com.vatos.core.injection.defaults.lifecycle.steps.read;

import tr.com.vatos.core.injection.defaults.meta.ConfigBeanMetaDataSource;
import tr.com.vatos.core.injection.defaults.meta.DefaultBeanSourceTypesEnum;
import tr.com.vatos.core.injection.v2.annotations.Bean;
import tr.com.vatos.core.injection.v2.lifecycle.steps.ReadBeanMetaDataStep;
import tr.com.vatos.core.injection.v2.meta.BeanMetaData;
import tr.com.vatos.core.injection.v2.meta.BeanSourceType;
import tr.com.vatos.core.reflections.classes.ClassManager;

import java.util.Set;
import java.util.stream.Collectors;

public class ConfigReadBeanMetaDataStep implements ReadBeanMetaDataStep<ConfigBeanMetaDataSource> {


    @Override
    public Set<BeanMetaData<ConfigBeanMetaDataSource>> readBeanMetaData(ConfigBeanMetaDataSource source) {
        return ClassManager.instance()
                .getMethodsFromClassWithGivenAnnotation(source.getClass(),Bean.class)
                .stream()
                .map(clz -> new BeanMetaData<ConfigBeanMetaDataSource>(source))
                .collect(Collectors.toSet());
    }

    @Override
    public BeanSourceType getSourceType() {
        return DefaultBeanSourceTypesEnum.CONFIG;
    }
}
