package hdu.homework.chat.entity.bean.database;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * created by 钱曹宇@supercode on 3/16/2020
 */
public class JWToken extends UsernamePasswordAuthenticationToken{

    public JWToken() {
        super(null, null);
    }

    public JWToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
