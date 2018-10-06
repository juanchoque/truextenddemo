package com.truextend.dev.recipes.security;

import com.truextend.dev.recipes.model.SecurityDataObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {


    private String secret = "tecnosim";

    public SecurityDataObject validate(String token) {

        SecurityDataObject securityDataObject = null;
        try {
            try {
                token = token.substring(token.indexOf(" ") + 1);
            }catch (Exception ex){

            }

            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            securityDataObject = new SecurityDataObject();

            //usuarios.setIdPersona(body.getSubject());
            securityDataObject.setIdAccount(Integer.parseInt(body.get("userId").toString()));
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("JWT Token is incorrect>");
        }

        return securityDataObject;
    }
}
