package tr.com.vatos.core.injection.temp;

import tr.com.vatos.core.injection.v2.annotations.Bean;

@Bean
public class Bean2 {

    public void init(){
        System.out.println("Bean2 init");
    }
}
