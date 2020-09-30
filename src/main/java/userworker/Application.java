package userworker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import userworker.config.SecurityConfig;
import userworker.config.WebConfig;

@SpringBootApplication(
        exclude = {HibernateJpaAutoConfiguration.class}
)
@Import({WebConfig.class, SecurityConfig.class})
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
