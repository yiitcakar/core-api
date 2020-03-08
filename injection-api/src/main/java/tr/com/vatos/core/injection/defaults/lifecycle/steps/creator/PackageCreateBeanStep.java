package tr.com.vatos.core.injection.defaults.lifecycle.steps.creator;

import tr.com.vatos.core.injection.defaults.meta.DefaultBeanSourceTypesEnum;
import tr.com.vatos.core.injection.v2.annotations.Init;
import tr.com.vatos.core.injection.v2.annotations.Inject;
import tr.com.vatos.core.injection.v2.lifecycle.steps.CreateBeanStep;
import tr.com.vatos.core.injection.v2.meta.BeanMetaData;
import tr.com.vatos.core.injection.v2.meta.BeanSourceType;
import tr.com.vatos.core.reflections.classes.ClassManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PackageCreateBeanStep implements CreateBeanStep {

    @Override
    public Object createBean(BeanMetaData beanMetaData, Map<String,Object> beanMap) {
        Object bean = null;
        try {
            bean = beanMetaData.getBeanClass().newInstance();
            List<Field> fields = ClassManager.instance().getFieldsFromClassWithAnnotation(beanMetaData.getBeanClass(), Inject.class);
            for(Field field : fields)
            {
                String dependencyName = field.getType().getName();
                field.setAccessible(true);
                field.set(bean,beanMap.get(dependencyName));
            }
            Collection<Method> initMethods = ClassManager.instance().getMethodsFromClassWithGivenAnnotation(beanMetaData.getBeanClass(), Init.class);
            for(Method initMethod : initMethods){
                initMethod.invoke(bean);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //TODO handle error!
        }
        return bean;
    }

    @Override
    public BeanSourceType getSourceType() {
        return DefaultBeanSourceTypesEnum.PACKAGE;
    }
}
