package tr.com.vatos.core.injection.v1.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Inject
@Target({ElementType.FIELD})
public @interface InjectField {
}
