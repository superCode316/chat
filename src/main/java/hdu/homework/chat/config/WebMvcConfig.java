package hdu.homework.chat.config;

import hdu.homework.chat.utils.GroupCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private GroupCheckInterceptor groupCheckInterceptor;
    public WebMvcConfig(GroupCheckInterceptor groupCheckInterceptor) {
        this.groupCheckInterceptor = groupCheckInterceptor;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedOrigins("*")
                .allowedMethods("*");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(groupCheckInterceptor)
                .addPathPatterns("/group/**")
                .addPathPatterns("/message/**")
                .order(1);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/public/**").addResourceLocations("classpath:/templates/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
