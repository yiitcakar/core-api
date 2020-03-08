package tr.com.vatos.core.injection.defaults.meta;

import tr.com.vatos.core.injection.v2.meta.BeanMetaDataSource;

import java.util.HashSet;
import java.util.Set;

public class ConfigBeanMetaDataSource extends BeanMetaDataSource {

    private final Class<?> configClass;

    public ConfigBeanMetaDataSource(Class<?> configClass) {
        super(DefaultBeanSourceTypesEnum.CONFIG);
        this.configClass = configClass;
    }

    public Class<?> getConfigClass() {
        return configClass;
    }
}
