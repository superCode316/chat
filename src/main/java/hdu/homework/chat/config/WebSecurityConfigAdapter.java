package hdu.homework.chat.config;

import hdu.homework.chat.utils.BeatsFilter;
import hdu.homework.chat.utils.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Configuration
public class WebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {

    private JwtAuthenticationFilter filter;
    private BeatsFilter beatsFilter;

    public WebSecurityConfigAdapter(JwtAuthenticationFilter filter, BeatsFilter beatsFilter) {
        this.filter = filter;
        this.beatsFilter = beatsFilter;
    }
    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
//                .antMatchers("/user/add-friend")
//                .antMatchers("/v2/**")
//                .antMatchers("/swagger.html")
//                .antMatchers("/user/login")
//                .antMatchers("/user/register")
//                .antMatchers("/message/**")
//                .antMatchers("/group/**");
        .antMatchers("/**");
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
                .addFilterBefore(getFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(beatsFilter, JwtAuthenticationFilter.class);
        super.configure(http);
    }


    @Bean
    public CorsFilter getFilter() {
        return new CorsFilter();
    }

}
