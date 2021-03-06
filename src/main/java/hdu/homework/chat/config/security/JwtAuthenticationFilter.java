package hdu.homework.chat.config.security;

import hdu.homework.chat.entity.bean.security.IgnoreURI;
import hdu.homework.chat.modules.user.service.AuthenticationService;
import hdu.homework.chat.utils.JSONTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private JSONTokenUtil util;
    private UserDetailsService service;
    private IgnoreURI ignore;
    private AuthenticationService authenticationService;
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        if (ignore.check(request.getRequestURI())) {
            return true;
        }
        return super.shouldNotFilter(request);
    }

    public JwtAuthenticationFilter(JSONTokenUtil util, @Qualifier("userdetails") UserDetailsService service, IgnoreURI ignore, AuthenticationService authenticationService) {
        this.util = util;
        this.service = service;
        this.ignore = ignore;
        this.authenticationService = authenticationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String header = httpServletRequest.getHeader(util.getHeader());
        log.info("header : {}", header);
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null && cookies.length!=0) {
            for (Cookie c : cookies) {
                if (c.getName().equals("x-access-token")) {
                    header = c.getValue();
                }
            }
        }
        UsernamePasswordAuthenticationToken token = null;
        if (header != null && !StringUtils.isEmpty(header)) {
            token = authenticationService.verifyCookie(header);
            if (token != null)
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
        }
        if (token == null) {
            httpServletResponse.sendError(401, "token未设置或者过期失效");
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(token);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
