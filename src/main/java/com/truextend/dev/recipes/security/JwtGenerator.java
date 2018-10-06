package com.truextend.dev.recipes.security;

import com.truextend.dev.recipes.model.SecurityDataObject;
import com.truextend.dev.recipes.util.RecipesUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtGenerator {


    public String generate(SecurityDataObject securityDataObject) {
        RecipesUtil recipesUtil = new RecipesUtil();

        Claims claims = Jwts.claims();

        claims.put("userId", String.valueOf(securityDataObject.getIdAccount()));
        Date date = recipesUtil.setLastTime(new Date());

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, "truextend")
                .compact();
    }
}
