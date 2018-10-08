package com.truextend.dev.recipes.security;

import com.truextend.dev.recipes.security.utilSecurity.JwtAuthenticationToken;
import com.truextend.dev.recipes.util.ConstantsRecipes;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    /**
     * add url for filter security
     */
    public JwtAuthenticationTokenFilter() {
        super("/accounts/add/**");
        this.setRequiresAuthenticationRequestMatcher(new OrRequestMatcher(
            new AntPathRequestMatcher("/accounts/add/**")
                ,new AntPathRequestMatcher("/recipes/add/**")
                ,new AntPathRequestMatcher("/recipes/get/**")
                ,new AntPathRequestMatcher("/recipes/delete/**")
        ));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {

        String header = httpServletRequest.getHeader("Authorization");
        if (header == null) {
            throw new RuntimeException(ConstantsRecipes.MISSING_TOKEN);
        }

        JwtAuthenticationToken token = new JwtAuthenticationToken(header);
        return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}
