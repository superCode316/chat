package hdu.homework.chat.config;

import hdu.homework.chat.utils.FriendCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private FriendCheckInterceptor friendCheckInterceptor;

    public WebMvcConfig(FriendCheckInterceptor friendCheckInterceptor) {
        this.friendCheckInterceptor = friendCheckInterceptor;
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
        registry.addInterceptor(friendCheckInterceptor)
                .addPathPatterns("/user/**")
                .addPathPatterns("/message/**")
                .order(1);
    }
}
