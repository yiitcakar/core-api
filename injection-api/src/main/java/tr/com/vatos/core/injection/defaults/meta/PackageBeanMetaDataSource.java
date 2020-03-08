package tr.com.vatos.core.injection.defaults.meta;

import tr.com.vatos.core.injection.v2.meta.BeanMetaDataSource;

import java.util.HashSet;
import java.util.Set;

public class PackageBeanMetaDataSource extends BeanMetaDataSource {

    private final Set<String> basePackages;

    public PackageBeanMetaDataSource() {
        super(DefaultBeanSourceTypesEnum.PACKAGE);
        this.basePackages = new HashSet<>();
    }

    public void addPackage(String pck){
        this.basePackages.add(pck);
    }

    public Set<String> getBasePackages() {
        return basePackages;
    }

    public String[] getBasePackagesAsArray() {
        return basePackages.toArray(new String[basePackages.size()]);
    }
}
