package org.example.config;

import org.example.Desktop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    //this is a bean and it can be accessed as Class.Desktop
    //or the mmethod name destkop or whatever method naeme was
    //@ bean will import it but is we want our bean name to be differenbt wwe use diferent approach as follow
    @Bean(name= {"RustamBean","desktopBean"})
    public Desktop desktop(){
        return new Desktop();
    }
}
