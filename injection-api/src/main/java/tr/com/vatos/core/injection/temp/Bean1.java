package tr.com.vatos.core.injection.temp;

import tr.com.vatos.core.injection.v2.annotations.Bean;
import tr.com.vatos.core.injection.v2.annotations.Inject;

@Bean
public class Bean1 {

    @Inject
    private Bean2 bean2;

    @Inject
    private Bean3 bean3;

    public void init(){
        System.out.println("Bean1 init");
    }
}
