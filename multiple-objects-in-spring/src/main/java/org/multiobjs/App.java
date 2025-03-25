package org.multiobjs;

import org.multiobjs.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Desktop dt = context.getBean("RustamBean",Desktop.class);
        dt.compile();
        //if thsi object in appconfig was not anotated as prototype scope it will throw error
        Desktop dt2 = context.getBean("RustamBean",Desktop.class);
        dt.compile();
    }

    public static interface Computer {

        void compile();
    }
}
