package com.revature.WebApp.security;

import com.revature.WebApp.DTO.Principal;
import com.revature.WebApp.entities.UserEntity;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Assert;
import org.junit.Test;

import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.StandardCharsets;
import java.security.Key;

import static org.mockito.Mockito.*;

public class TokenGeneratorTests {
    Principal principal = new Principal();
    JwtConfig jwtConfig = new JwtConfig();
    Principal spyPrincipal = spy(Principal.class);
//    JwtConfig spyJwt = spy(JwtConfig.class);
    JwtConfig spyJwt = spy(jwtConfig);//spy(JwtConfig.class);

    JwtConfig mockJwt = mock(JwtConfig.class);
    TokenGenerator sut = new TokenGenerator(spyJwt);

    @Test
    public void testCreateJwt() {

        //Arrange
        String prefix = "Authorization";
        String s;
        Key key = new SecretKeySpec("hello".getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.RS512.getJcaName());
        //when(spyPrincipal.getRole()).thenReturn(UserEntity.Role.BASIC_USER);
        when(spyJwt.getSigningKey()).thenReturn(key);
        when(spyJwt.getPrefix()).thenReturn(prefix);

        //Act
        s = sut.createJwt(spyPrincipal);

        //Assert
        verify(spyJwt,times(1)).getExpiration();
        verify(spyJwt, times(1)).getSigAlg();
        verify(spyJwt, times(1)).getSigningKey();
        verify(spyJwt, times(1)).getPrefix();

        Assert.assertNotNull(s);
        System.out.println(s);
        Assert.assertTrue(s.contains(prefix));
    }
}
