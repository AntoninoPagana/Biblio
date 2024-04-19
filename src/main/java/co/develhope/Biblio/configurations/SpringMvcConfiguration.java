package co.develhope.Biblio.configurations;

import co.develhope.Biblio.interceptors.BookInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class SpringMvcConfiguration implements WebMvcConfigurer {
    private BookInterceptor bookInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(bookInterceptor);
    }
}
