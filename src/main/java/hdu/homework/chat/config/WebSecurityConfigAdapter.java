package hdu.homework.chat.config;

import hdu.homework.chat.utils.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {

    private JwtAuthenticationFilter filter;

    public WebSecurityConfigAdapter(JwtAuthenticationFilter filter) {
        this.filter = filter;
    }
    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/user/add-friend")
                .antMatchers("/user/login")
                .antMatchers("/user/register")
                .antMatchers("/message/**")
                .antMatchers("/group/**");
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        super.configure(http);
    }
}
