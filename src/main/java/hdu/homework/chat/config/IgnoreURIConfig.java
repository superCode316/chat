package hdu.homework.chat.config;

import hdu.homework.chat.entity.bean.security.IgnoreURI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * created by 钱曹宇@supercode on 3/13/2020
 */
@Configuration
public class IgnoreURIConfig {
    @Bean
    public IgnoreURI getIgnoreURI() {
        IgnoreURI ignoreURI = new IgnoreURI();
        ignoreURI.add("/auth/login", "/auth/logout", "/auth/register", "/public/**", "/static/**", "/favicon.ico", "/swagger-ui.html", "/swagger-ui.html/**", "/webjars/**", "/v2/**");
        return ignoreURI;
    }
}
