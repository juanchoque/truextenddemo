package com.truextend.dev.recipes.security;

import com.truextend.dev.recipes.model.SecurityDataObject;
import com.truextend.dev.recipes.security.utilSecurity.JwtAuthenticationToken;
import com.truextend.dev.recipes.security.utilSecurity.JwtUserDetails;
import com.truextend.dev.recipes.security.utilSecurity.SecurityServiceImpl;
import com.truextend.dev.recipes.util.ConstantsRecipes;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    public JwtAuthenticationProvider() {
    }

    public JwtAuthenticationProvider(SecurityServiceImpl securityServiceImpl) {
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) usernamePasswordAuthenticationToken;
        String token = jwtAuthenticationToken.getToken();

        JwtValidator validator = new JwtValidator();
        SecurityDataObject securityDataObject = null;

            securityDataObject = validator.validate(token);

            if (securityDataObject.getIdAccount() == null) {
                throw new RuntimeException(ConstantsRecipes.INCORRECT_TOKEN);
            }

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(String.valueOf(securityDataObject.getIdRol()));
        return new JwtUserDetails(securityDataObject.getIdAccount(),
                token,
                grantedAuthorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return (JwtAuthenticationToken.class.isAssignableFrom(aClass));
    }
}
