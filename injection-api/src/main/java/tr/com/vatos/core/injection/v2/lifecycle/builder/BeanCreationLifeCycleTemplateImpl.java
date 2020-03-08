package tr.com.vatos.core.injection.v2.lifecycle.builder;

import tr.com.vatos.core.injection.v2.lifecycle.BeanCreationLifeCycleTemplate;
import tr.com.vatos.core.injection.v2.lifecycle.steps.*;
import tr.com.vatos.core.injection.v2.meta.BeanMetaData;
import tr.com.vatos.core.injection.v2.meta.BeanMetaDataSource;
import tr.com.vatos.core.injection.v2.meta.BeanSourceType;

import java.util.*;

public final class BeanCreationLifeCycleTemplateImpl implements BeanCreationLifeCycleTemplate {

    //steps
    private final Map<BeanSourceType, ReadBeanMetaDataStep> readBeanMetaDataStepMap;
    private final CombineBeanMetaDataStep combineBeanMetaDataStep;
    private final Set<ValidateBeanMetaDataStep> validateBeanMetaDataStepSet;
    private final OrganizeBeanMetaDataStep organizeBeanMetaDataStep;
    private final Map<BeanSourceType, CreateBeanStep>  createBeanStepMap;

    protected BeanCreationLifeCycleTemplateImpl(BCLCTBuilder.Context builderContext)
    {
        this.readBeanMetaDataStepMap = builderContext.getReadBeanMetaDataStepMap();
        this.combineBeanMetaDataStep = builderContext.getCombineBeanMetaDataStep();
        this.validateBeanMetaDataStepSet = builderContext.getValidateBeanMetaDataStepSet();
        this.organizeBeanMetaDataStep = builderContext.getOrganizeBeanMetaDataStep();
        this.createBeanStepMap = builderContext.getCreateBeanStepMap();
    }

    @Override
    public Map<String, Object> startBeanCreationLifeCycle(Set<BeanMetaDataSource> metaDataSource) {
        List<Set<BeanMetaData>> beanMetadataSourceList = readAllBeanMetaData(metaDataSource);
        Set<BeanMetaData> allBeanMetaData = combineAllBeanMetaData(beanMetadataSourceList);
        validateAllBeanMetaData(allBeanMetaData);
        Map<Integer,Set<BeanMetaData>> orderedAllBeanMetaData = orderAllBeanMetaData(allBeanMetaData);
        return createAllBeans(orderedAllBeanMetaData);
    }

    private List<Set<BeanMetaData>> readAllBeanMetaData(Set<BeanMetaDataSource> sources) {
        List<Set<BeanMetaData>> beanMetadataSourceList = new ArrayList<>();
        for (BeanMetaDataSource source : sources) {
            if (readBeanMetaDataStepMap.containsKey(source.getBeanSourceType())) {
                ReadBeanMetaDataStep readBeanMetaDataStep = readBeanMetaDataStepMap.get(source.getBeanSourceType());
                beanMetadataSourceList.add(readBeanMetaDataStep.readBeanMetaData(source));
            } else {
                //TODO throw exception
            }
        }
        return beanMetadataSourceList;
    }

    private Set<BeanMetaData> combineAllBeanMetaData(List<Set<BeanMetaData>> beanMetadataSourceList) {
        Set<BeanMetaData> allBeanMetaData = new HashSet<>();
        for (Set<BeanMetaData> beanMetaDataSet : beanMetadataSourceList) {
            combineBeanMetaDataStep.combine(allBeanMetaData, beanMetaDataSet);
        }
        return allBeanMetaData;
    }

    private void validateAllBeanMetaData(Set<BeanMetaData> allBeanMetaData) {
        for (ValidateBeanMetaDataStep validateBeanMetaDataStep : validateBeanMetaDataStepSet) {
            if (!validateBeanMetaDataStep.validateBeanMetaData(allBeanMetaData)) {
                //TODO throw exception
            }
        }
    }

    private Map<Integer,Set<BeanMetaData>> orderAllBeanMetaData(Set<BeanMetaData> allBeanMetaData) {
        return organizeBeanMetaDataStep.organizeBeanMetaData(allBeanMetaData);
    }

    private Map<String,Object> createAllBeans(Map<Integer,Set<BeanMetaData>> orderedAllBeanMetaData)
    {
        Map<String,Object> allBeanMap = new HashMap<>();
        Set<Integer> orders = orderedAllBeanMetaData.keySet();
        for(Integer order : orders)
        {
            Set<BeanMetaData> metaDataSet = orderedAllBeanMetaData.get(order);
            for(BeanMetaData beanMetaData : metaDataSet)
            {
                if(createBeanStepMap.containsKey(beanMetaData.getBeanMetaDataSource().getBeanSourceType()))
                {
                    CreateBeanStep createBeanStep = createBeanStepMap.get(beanMetaData.getBeanMetaDataSource().getBeanSourceType());
                    Object bean = createBeanStep.createBean(beanMetaData,allBeanMap);
                    allBeanMap.put(beanMetaData.getBeanName(),bean);
                }
            }
        }
        return allBeanMap;
    }


}
