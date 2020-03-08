package tr.com.vatos.core.injection.v2;

import tr.com.vatos.core.injection.defaults.lifecycle.steps.combine.DefaultCombineBeanMetaDataStep;
import tr.com.vatos.core.injection.defaults.lifecycle.steps.creator.PackageCreateBeanStep;
import tr.com.vatos.core.injection.defaults.lifecycle.steps.organize.DefaultOrganizeBeanMetaDataStep;
import tr.com.vatos.core.injection.defaults.lifecycle.steps.read.ConfigReadBeanMetaDataStep;
import tr.com.vatos.core.injection.defaults.lifecycle.steps.read.PackageReadBeanMetaDataStep;
import tr.com.vatos.core.injection.defaults.lifecycle.steps.validator.ValidateCyclingDependencyBeanMetaDataStep;
import tr.com.vatos.core.injection.defaults.meta.PackageBeanMetaDataSource;
import tr.com.vatos.core.injection.v2.lifecycle.BeanCreationLifeCycleTemplate;
import tr.com.vatos.core.injection.v2.meta.BeanMetaDataSource;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TestApp {
    public static void main(String[] args) {

        BeanCreationLifeCycleTemplate bclft = BeanCreationLifeCycleTemplate.builder()
                .readers()
                    .addReaderStep(new ConfigReadBeanMetaDataStep())
                    .addReaderStep(new PackageReadBeanMetaDataStep())
                .end()
                .combiner(new DefaultCombineBeanMetaDataStep())
                .validators()
                    .addValidatorStep(new ValidateCyclingDependencyBeanMetaDataStep())
                .end()
                .organizer(new DefaultOrganizeBeanMetaDataStep())
                .creators()
                    .addBeanCreatorStep(new PackageCreateBeanStep())
                .end()
                .build();

        Set<BeanMetaDataSource> sources = new HashSet<>();
        PackageBeanMetaDataSource s1 = new PackageBeanMetaDataSource();
        s1.addPackage("tr.com.vatos.core.injection.temp");
        sources.add(s1);

        Map<String, Object> beanMap = bclft.startBeanCreationLifeCycle(sources);

        System.out.println("done");

    }
}
