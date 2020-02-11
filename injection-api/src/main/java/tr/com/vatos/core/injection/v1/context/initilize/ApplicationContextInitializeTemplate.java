package tr.com.vatos.core.injection.v1.context.initilize;

import tr.com.vatos.core.common.utils.NullCheckUtils;
import tr.com.vatos.core.injection.v1.annotations.Bean;
import tr.com.vatos.core.injection.v1.annotations.Configuration;
import tr.com.vatos.core.reflections.classes.ClassManager;
import tr.com.vatos.core.reflections.packages.PackageManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
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




}
