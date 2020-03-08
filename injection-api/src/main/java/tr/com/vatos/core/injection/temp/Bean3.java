package tr.com.vatos.core.injection.temp;

import tr.com.vatos.core.injection.v2.annotations.Bean;
import tr.com.vatos.core.injection.v2.annotations.Inject;

@Bean
public class Bean3 {

    @Inject
    private Bean2 bean2;

    public void init(){
        System.out.println("Bean3 init");
    }
}
