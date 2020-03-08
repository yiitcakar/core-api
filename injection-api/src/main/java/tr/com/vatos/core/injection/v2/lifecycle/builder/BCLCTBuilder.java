package tr.com.vatos.core.injection.v2.lifecycle.builder;

import tr.com.vatos.core.builder.impl.line.StartBuilder;
import tr.com.vatos.core.builder.types.BuilderContext;
import tr.com.vatos.core.injection.v2.lifecycle.steps.*;
import tr.com.vatos.core.injection.v2.meta.BeanSourceType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BCLCTBuilder extends StartBuilder<BCLCTBuilder.Context>
{
    public BCLCTReaderBuilder readers()
    {
        return new BCLCTReaderBuilder(new Context());
    }

    public static class Context implements BuilderContext
    {

        private Context(){
            this.readBeanMetaDataStepMap = new HashMap<>();
            this.validateBeanMetaDataStepSet = new HashSet<>();
            this.createBeanStepMap = new HashMap<>();
        }

        private final Map<BeanSourceType, ReadBeanMetaDataStep> readBeanMetaDataStepMap;
        private CombineBeanMetaDataStep combineBeanMetaDataStep;
        private final Set<ValidateBeanMetaDataStep> validateBeanMetaDataStepSet;
        private OrganizeBeanMetaDataStep organizeBeanMetaDataStep;
        private final Map<BeanSourceType, CreateBeanStep>  createBeanStepMap;

        public void addReadBeanMetaDataStepMap(BeanSourceType key,ReadBeanMetaDataStep val){
            this.readBeanMetaDataStepMap.put(key,val);
        }

        public void addValidateBeanMetaDataStepSet(ValidateBeanMetaDataStep val){
            this.validateBeanMetaDataStepSet.add(val);
        }

        public void addCreateBeanStepMap(BeanSourceType key,CreateBeanStep val){
            this.createBeanStepMap.put(key,val);
        }

        public void setCombineBeanMetaDataStep(CombineBeanMetaDataStep combineBeanMetaDataStep) {
            this.combineBeanMetaDataStep = combineBeanMetaDataStep;
        }

        public void setOrganizeBeanMetaDataStep(OrganizeBeanMetaDataStep organizeBeanMetaDataStep) {
            this.organizeBeanMetaDataStep = organizeBeanMetaDataStep;
        }

        public Map<BeanSourceType, ReadBeanMetaDataStep> getReadBeanMetaDataStepMap() {
            return readBeanMetaDataStepMap;
        }

        public CombineBeanMetaDataStep getCombineBeanMetaDataStep() {
            return combineBeanMetaDataStep;
        }

        public Set<ValidateBeanMetaDataStep> getValidateBeanMetaDataStepSet() {
            return validateBeanMetaDataStepSet;
        }

        public OrganizeBeanMetaDataStep getOrganizeBeanMetaDataStep() {
            return organizeBeanMetaDataStep;
        }

        public Map<BeanSourceType, CreateBeanStep> getCreateBeanStepMap() {
            return createBeanStepMap;
        }
    }
}
