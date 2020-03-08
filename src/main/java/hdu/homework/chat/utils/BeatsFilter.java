package hdu.homework.chat.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Component
public class BeatsFilter extends OncePerRequestFilter {
    private RedisTemplate<String, String> redisTemplate;

    public BeatsFilter(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            redisTemplate.opsForValue().set(username, "online", 60, TimeUnit.SECONDS);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
