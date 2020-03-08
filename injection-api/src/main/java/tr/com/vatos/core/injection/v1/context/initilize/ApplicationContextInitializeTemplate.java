package tr.com.vatos.core.injection.v1.context.initilize;

import tr.com.vatos.core.common.utils.NullCheckUtils;
import tr.com.vatos.core.dynamicproxy.action.ProxyAction;
import tr.com.vatos.core.dynamicproxy.constants.enums.ProxyActionDecision;
import tr.com.vatos.core.injection.v1.annotations.Bean;
import tr.com.vatos.core.injection.v1.annotations.Configuration;
import tr.com.vatos.core.reflections.classes.ClassManager;
import tr.com.vatos.core.reflections.packages.PackageManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.Map;

public class ApplicationContextInitializeTemplate
{
    private final ClassManager classManager;
    private final PackageManager packageManager;

    public ApplicationContextInitializeTemplate(){
        this.classManager = ClassManager.instance();
        this.packageManager = PackageManager.instance();
    }

    public void init(final Map<String,Object> beanMap,Class<?> configClass)
    {
        if(classManager.classHasAnnotation(configClass, Configuration.class))
        {
            Configuration configuration = classManager.getAnnotationFromClassWithoutNullCheck(configClass,Configuration.class);
            creatBeansOnPackages(beanMap,configuration.packages());
            creatBeansOnConfig(beanMap,configClass);
            injectBeans(beanMap);
            executeInitMethods(beanMap);
        }
        else
        {
            //TODO throw exception!
        }
    }

    protected void creatBeansOnPackages(final Map<String,Object> beanMap,String... packages)
    {
        if(NullCheckUtils.isNotArrayEmpty(packages))
        {
            packageManager.getAllClassesWithAnnotation(Bean.class,packages)
                    .stream()
                    .forEach(cls ->
                    {
                        try
                        {
                            Object bean = cls.newInstance();
                            beanMap.put(cls.getName(),bean);
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    });
        }
    }

    protected void creatBeansOnConfig(final Map<String,Object> beanMap,Class<?> configClass)
    {
        if(NullCheckUtils.isNotObjectEmpty(configClass))
        {
            try
            {
                Object configObject = configClass.newInstance();
                classManager.getMethodsFromClassWithGivenAnnotation(configClass,Bean.class)
                        .stream()
                        .forEach(method ->
                        {
                            try
                            {
                                Parameter[] parameters = method.getParameters();
                                Object bean = method.invoke(configObject);
                                beanMap.put(bean.getClass().getName(),bean);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        });

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

    }

    protected void injectBeans(final Map<String,Object> beanMap)
    {

    }

    protected void executeInitMethods(final Map<String,Object> beanMap)
    {

    }

    private static final class BeanInitializationProxyAction implements ProxyAction
    {
        private final Map<String,Object> beanMap;
        private String name;

        protected BeanInitializationProxyAction(Map<String,Object> beanMap){
            this.beanMap = beanMap;
        }

        @Override
        public ProxyActionDecision makeDecision(Object instance, String methodName, Class<?> returnType, Object... args) {
            this.name = returnType.getName();
            return beanMap.containsKey(name)
                    ? ProxyActionDecision.OR_ELSE
                    : ProxyActionDecision.CALL_SUPER;
        }

        @Override
        public Object callSuperThenReturn(Object object) {
            beanMap.put(name,object);
            return beanMap.get(name);
        }

        @Override
        public Object orElseReturn() {
            return beanMap.get(name);
        }
    }




}
