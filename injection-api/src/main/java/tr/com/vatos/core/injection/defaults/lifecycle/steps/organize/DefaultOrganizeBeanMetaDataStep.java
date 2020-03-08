package tr.com.vatos.core.injection.defaults.lifecycle.steps.organize;

import tr.com.vatos.core.common.utils.NullCheckUtils;
import tr.com.vatos.core.injection.v2.lifecycle.steps.OrganizeBeanMetaDataStep;
import tr.com.vatos.core.injection.v2.meta.BeanMetaData;

import java.util.*;
import java.util.stream.Collectors;

public class DefaultOrganizeBeanMetaDataStep implements OrganizeBeanMetaDataStep {

    @Override
    public Map<Integer,Set<BeanMetaData>> organizeBeanMetaData(Set<BeanMetaData> allBeanMetaData) {
        Map<Integer,Set<BeanMetaData>> result = new HashMap<>();
        Map<String,BeanMetaData> metaDataMap = allBeanMetaData.stream().collect(Collectors.toMap(k->k.getBeanName(), v->v));
        Map<String,Integer> depthMap = new HashMap<>();
        for(BeanMetaData beanMetaData : allBeanMetaData)
        {
            int depth = getDepthRec(beanMetaData, depthMap, metaDataMap);
            if(!result.containsKey(depth)){
                result.put(depth,new HashSet<>());
            }
            result.get(depth).add(beanMetaData);
        }
        return result;
    }

    public int getDepthRec(BeanMetaData current,Map<String,Integer> depthMap,Map<String,BeanMetaData> metaDataMap)
    {
        int depth = 0;
        int dependencyDepth;
        Collection<String> dependencies = current.getDependencies();
        for(String dependency : dependencies)
        {
            if(depthMap.containsKey(dependency)){
                dependencyDepth = depthMap.get(dependency);
            }else{
                dependencyDepth = getDepthRec(metaDataMap.get(dependency),depthMap,metaDataMap);
            }
            if(dependencyDepth >= depth){
                depth = dependencyDepth + 1;
            }
        }
        depthMap.put(current.getBeanName(),depth);
        return depth;
    }


}
