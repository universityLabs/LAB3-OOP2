package com.tech.zootech.security.utils.realization;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.auth0.jwt.algorithms.Algorithm;
import com.tech.zootech.security.exceptions.RefreshTokenException;
import com.tech.zootech.security.service.UserService;
import com.tech.zootech.security.utils.contracts.AlgorithmUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.io.PipedOutputStream;

import org.apache.catalina.connector.Response;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.DelegatingServletOutputStream;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {RefreshTokenUtilImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class RefreshTokenUtilImplTest {
    @MockBean
    private AlgorithmUtil algorithmUtil;

    @Autowired
    private RefreshTokenUtilImpl refreshTokenUtilImpl;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link RefreshTokenUtilImpl#refresh(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    public void testRefresh() throws IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        assertThrows(RefreshTokenException.class, () -> refreshTokenUtilImpl.refresh(request, new Response()));
    }

    /**
     * Method under test: {@link RefreshTokenUtilImpl#refresh(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    public void testRefresh3() throws IOException {
        HttpServletRequestWrapper httpServletRequestWrapper = mock(HttpServletRequestWrapper.class);
        when(httpServletRequestWrapper.getHeader((String) any())).thenReturn("https://example.org/example");
        assertThrows(RefreshTokenException.class,
                () -> refreshTokenUtilImpl.refresh(httpServletRequestWrapper, new Response()));
        verify(httpServletRequestWrapper).getHeader((String) any());
    }


    /**
     * Method under test: {@link RefreshTokenUtilImpl#refresh(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    public void testRefresh5() throws IOException {
        when(algorithmUtil.getAlgorithm()).thenReturn(null);
        HttpServletRequestWrapper httpServletRequestWrapper = mock(HttpServletRequestWrapper.class);
        when(httpServletRequestWrapper.getHeader((String) any())).thenReturn(RefreshTokenUtilImpl.AUTH_SUBSTR);
        HttpServletResponseWrapper httpServletResponseWrapper = mock(HttpServletResponseWrapper.class);
        when(httpServletResponseWrapper.getOutputStream())
                .thenReturn(new DelegatingServletOutputStream(new ByteArrayOutputStream()));
        doNothing().when(httpServletResponseWrapper).setHeader((String) any(), (String) any());
        doNothing().when(httpServletResponseWrapper).setStatus(anyInt());
        refreshTokenUtilImpl.refresh(httpServletRequestWrapper, httpServletResponseWrapper);
        verify(algorithmUtil).getAlgorithm();
        verify(httpServletRequestWrapper).getHeader((String) any());
        verify(httpServletResponseWrapper).getOutputStream();
        verify(httpServletResponseWrapper).setHeader((String) any(), (String) any());
        verify(httpServletResponseWrapper).setStatus(anyInt());
    }

    /**
     * Method under test: {@link RefreshTokenUtilImpl#refresh(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    public void testRefresh6() throws IOException {
        when(algorithmUtil.getAlgorithm()).thenReturn(mock(Algorithm.class));
        HttpServletRequestWrapper httpServletRequestWrapper = mock(HttpServletRequestWrapper.class);
        when(httpServletRequestWrapper.getHeader((String) any())).thenReturn(RefreshTokenUtilImpl.AUTH_SUBSTR);
        HttpServletResponseWrapper httpServletResponseWrapper = mock(HttpServletResponseWrapper.class);
        when(httpServletResponseWrapper.getOutputStream())
                .thenReturn(new DelegatingServletOutputStream(new ByteArrayOutputStream()));
        doNothing().when(httpServletResponseWrapper).setHeader((String) any(), (String) any());
        doNothing().when(httpServletResponseWrapper).setStatus(anyInt());
        refreshTokenUtilImpl.refresh(httpServletRequestWrapper, httpServletResponseWrapper);
        verify(algorithmUtil).getAlgorithm();
        verify(httpServletRequestWrapper).getHeader((String) any());
        verify(httpServletResponseWrapper).getOutputStream();
        verify(httpServletResponseWrapper).setHeader((String) any(), (String) any());
        verify(httpServletResponseWrapper).setStatus(anyInt());
    }
}

