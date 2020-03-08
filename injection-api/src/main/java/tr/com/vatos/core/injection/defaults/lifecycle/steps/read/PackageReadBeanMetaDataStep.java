package tr.com.vatos.core.injection.defaults.lifecycle.steps.read;

import tr.com.vatos.core.injection.defaults.meta.DefaultBeanSourceTypesEnum;
import tr.com.vatos.core.injection.defaults.meta.PackageBeanMetaDataSource;
import tr.com.vatos.core.injection.v2.annotations.Bean;
import tr.com.vatos.core.injection.v2.annotations.Inject;
import tr.com.vatos.core.injection.v2.lifecycle.steps.ReadBeanMetaDataStep;
import tr.com.vatos.core.injection.v2.meta.BeanMetaData;
import tr.com.vatos.core.injection.v2.meta.BeanSourceType;
import tr.com.vatos.core.reflections.classes.ClassManager;
import tr.com.vatos.core.reflections.packages.PackageManager;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PackageReadBeanMetaDataStep implements ReadBeanMetaDataStep<PackageBeanMetaDataSource> {

    @Override
    public Set<BeanMetaData<PackageBeanMetaDataSource>> readBeanMetaData(PackageBeanMetaDataSource source) {
        return PackageManager.instance()
                .getAllClassesWithAnnotation(Bean.class, source.getBasePackagesAsArray())
                .stream()
                .map(clz -> 
                {
                    BeanMetaData<PackageBeanMetaDataSource> result = new BeanMetaData<PackageBeanMetaDataSource>(source);
                    result.setBeanClass(clz);
                    result.setBeanName(clz.getName());
                    List<String> dependencies = ClassManager.instance().getFieldsFromClassWithAnnotation(clz, Inject.class)
                            .stream()
                            .map(field -> field.getType().getName())
                            .collect(Collectors.toList());
                    result.setDependencies(dependencies);
                    return result;
                })
                .collect(Collectors.toSet());
    }

    @Override
    public BeanSourceType getSourceType() {
        return DefaultBeanSourceTypesEnum.PACKAGE;
    }
}
