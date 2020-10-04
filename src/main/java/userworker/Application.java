package userworker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import userworker.config.SecurityConfig;
import userworker.config.WebConfig;

@SpringBootApplication(
        exclude = {
                HibernateJpaAutoConfiguration.class,
        }
)
@Import({WebConfig.class, SecurityConfig.class})
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
