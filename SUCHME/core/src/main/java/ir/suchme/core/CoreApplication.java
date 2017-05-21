package ir.suchme.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by mohammad on 5/20/17.
 */
@SpringBootApplication(scanBasePackages = {"ir.suchme.core","ir.suchme.core.controller"})
public class CoreApplication extends SpringBootServletInitializer {
        @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
            return application.sources(CoreApplication.class);
        }
    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class,args);
    }
}
