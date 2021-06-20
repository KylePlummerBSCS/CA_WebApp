package com.revature.WebApp.security;

import com.revature.WebApp.DTO.Principal;
import io.jsonwebtoken.MalformedJwtException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import java.security.Key;

import static org.mockito.Mockito.*;

public class TokenParserTests {

    String header = "Bearer Authentication";
    String prefix = "Bearer";
    JwtConfig mockJwtConfig;
    HttpServletRequest mockReq;
    Principal mockPrincipal;
    Key spyKey;
    TokenParser sut;

    @Before
    public void setUp() {
        mockJwtConfig = mock(JwtConfig.class);
        mockReq = mock(HttpServletRequest.class);
        mockPrincipal = mock(Principal.class);
        spyKey = spy(Key.class);
        sut = new TokenParser(mockJwtConfig);
    }

    @After
    public void teardDown() {
        mockJwtConfig = null;
        mockReq = null;
        mockPrincipal = null;
        sut = null;
    }

    @Test
    public void testCheckTokenWithNullHeader() {
        //Arrange

        when(mockJwtConfig.getHeader()).thenReturn(null);
        when(mockReq.getHeader(anyString())).thenReturn(null);

        //Act
        sut.checkToken(mockReq);

        //Assert
        verify(mockReq, times(1)).getHeader(any());
        verify(mockJwtConfig,times(1)).getHeader();
        verify(mockJwtConfig, times(0)).getPrefix();
        verify(mockJwtConfig, times(0)).getSigningKey();
        verify(mockReq, times(0)).setAttribute(anyString(),any());

        Assert.assertNull(mockReq.getHeader(mockJwtConfig.getHeader()));
    }

    @Test
    public void testCheckTokenWithValidHeader() {
        //Cannot fake/stub Jwts signing, but it's
        //fine so long as we get past the if-block.

        //Arrange
        JwtConfig spyJwtConfig = spy(JwtConfig.class);
        mockReq = spy(HttpServletRequest.class);

        when(mockReq.getHeader(spyJwtConfig.getHeader())).thenReturn(header);
        when(mockJwtConfig.getPrefix()).thenReturn(prefix);
        when(mockJwtConfig.getSigningKey()).thenThrow(MalformedJwtException.class);

        //Act
        sut.checkToken(mockReq);

        //Assert
        verify(mockReq, times(1)).getHeader(any());
        verify(mockJwtConfig,times(1)).getHeader();
        verify(mockJwtConfig, times(2)).getPrefix();
        verify(mockJwtConfig, times(1)).getSigningKey();
//        verify(mockReq, times(1)).setAttribute("principal", mockPrincipal);
    }
}
