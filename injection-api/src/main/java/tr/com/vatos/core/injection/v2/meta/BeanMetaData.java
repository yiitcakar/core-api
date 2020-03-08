package tr.com.vatos.core.injection.v2.meta;

import tr.com.vatos.core.builder.impl.SimpleBuilder;

import java.util.Collection;

public final class BeanMetaData<S extends BeanMetaDataSource>
{
    private String beanName;
    private Collection<String> dependencies;
    private Class<?> beanClass;
    private BeanCreationType beanCreationType;
    private S beanMetaDataSource;

    public BeanMetaData(S source){
        this.beanMetaDataSource = source;
    }

    public String getBeanName() {
        return beanName;
    }

    public Collection<String> getDependencies() {
        return dependencies;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public BeanCreationType getBeanCreationType() {
        return beanCreationType;
    }

    public S getBeanMetaDataSource() {
        return beanMetaDataSource;
    }


    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public void setDependencies(Collection<String> dependencies) {
        this.dependencies = dependencies;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }
}
