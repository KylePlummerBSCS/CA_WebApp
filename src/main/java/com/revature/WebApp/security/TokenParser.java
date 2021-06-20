package com.revature.WebApp.security;

import com.revature.WebApp.DTO.Principal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Null;

@Component
public class TokenParser {

    private JwtConfig jwtConfig;

    @Autowired
    public TokenParser(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    /**
     * The token generator uses the secret value and the hashing algorithm to put together a key
     * The key is used to encrypt and decrypt the JWT.
     *
     * @param request
     */
    public void checkToken(HttpServletRequest request) throws NullPointerException {
        String header = request.getHeader(jwtConfig.getHeader());

        if(header == null || !header.startsWith(jwtConfig.getPrefix())) {
            //some sort of error/exception
            return;
        }

        String token = header.replaceAll(jwtConfig.getPrefix(), "");



        Claims claims = Jwts.parser()
                .setSigningKey(jwtConfig.getSigningKey())
                .parseClaimsJws(token)
                .getBody();

        Principal principal = new Principal(claims);

        request.setAttribute("principal", principal);


    }
}
