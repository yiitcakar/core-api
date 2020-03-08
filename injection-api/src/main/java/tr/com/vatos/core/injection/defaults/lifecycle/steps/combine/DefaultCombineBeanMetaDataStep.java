package tr.com.vatos.core.injection.defaults.lifecycle.steps.combine;

import tr.com.vatos.core.injection.v2.lifecycle.steps.CombineBeanMetaDataStep;
import tr.com.vatos.core.injection.v2.meta.BeanMetaData;

import java.util.Set;
import java.util.stream.Collectors;

public class DefaultCombineBeanMetaDataStep implements CombineBeanMetaDataStep {

    @Override
    public void combine(Set<BeanMetaData> root, Set<BeanMetaData> source) {
        root.addAll(source.stream()
                .filter(item -> !root.contains(item))
                .collect(Collectors.toSet()));
    }
}
