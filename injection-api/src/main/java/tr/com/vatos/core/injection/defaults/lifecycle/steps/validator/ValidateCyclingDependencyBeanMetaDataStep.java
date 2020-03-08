package tr.com.vatos.core.injection.defaults.lifecycle.steps.validator;

import tr.com.vatos.core.injection.v2.lifecycle.steps.ValidateBeanMetaDataStep;
import tr.com.vatos.core.injection.v2.meta.BeanMetaData;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidateCyclingDependencyBeanMetaDataStep implements ValidateBeanMetaDataStep {

    @Override
    public boolean validateBeanMetaData(Set<BeanMetaData> allBeanMetaData) {
        Map<String,BeanMetaData> metaDataMap = allBeanMetaData.stream().collect(Collectors.toMap(k->k.getBeanName(),v->v));
        Set<String> visitedBeanNames = new HashSet<>();
        Collection<BeanMetaData> values = metaDataMap.values();
        for(BeanMetaData value : values)
        {
            if(!visitedBeanNames.contains(value.getBeanName()))
            {
                if(!resolveDependencyRec(value, new HashSet<>(), visitedBeanNames, metaDataMap)){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean resolveDependencyRec(BeanMetaData current,Set<String> edges,Set<String> visitedBeanNames,Map<String,BeanMetaData> metaDataMap)
    {
        if(edges.contains(current.getBeanName()))
        {
            //TODO throw excepiton
            System.out.println("cycle!");
            return false;
        }
        else
        {
            edges.add(current.getBeanName());
            Collection<String> dependencies = current.getDependencies();
            for(String dependency : dependencies)
            {
                if(!visitedBeanNames.contains(dependency))
                {
                    if(!resolveDependencyRec(metaDataMap.get(dependency),edges,visitedBeanNames,metaDataMap)){
                        return false;
                    }
                }
            }
            visitedBeanNames.add(current.getBeanName());
            return true;
        }
    }


}
