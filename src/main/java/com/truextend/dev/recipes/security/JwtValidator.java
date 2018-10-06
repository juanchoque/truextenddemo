package com.truextend.dev.recipes.security;

import com.truextend.dev.recipes.model.SecurityDataObject;
import com.truextend.dev.recipes.util.ConstantsRecipes;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {

    public SecurityDataObject validate(String token) {

        SecurityDataObject securityDataObject = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(ConstantsRecipes.SECRET)
                    .parseClaimsJws(token)
                    .getBody();

            securityDataObject = new SecurityDataObject();

            securityDataObject.setIdAccount(Integer.parseInt(body.get("userId").toString()));
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("JWT Token is incorrect>");
        }

        return securityDataObject;
    }
}
