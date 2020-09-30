package userworker.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import userworker.config.WebConfig;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    // Этот метод должен содержать конфигурации которые инициализируют Beans
    // для инициализации бинов у нас использовалась аннотация @Bean
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
                WebConfig.class, SecurityConfig.class
        };
    }

    // Тут добавляем конфигурацию, в которой инициализируем ViewResolver
    @Override
    protected Class<?>[] getServletConfigClasses() {

        return new Class<?>[]{
                WebConfig.class, SecurityConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}