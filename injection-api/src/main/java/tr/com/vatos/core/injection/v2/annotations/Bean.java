package tr.com.vatos.core.injection.v2.annotations;

import tr.com.vatos.core.injection.defaults.meta.DefaultBeanCreationTypesEnum;
import tr.com.vatos.core.injection.v2.meta.BeanCreationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface Bean {
    String name() default "";
    DefaultBeanCreationTypesEnum creationType() default DefaultBeanCreationTypesEnum.SELF;
}
