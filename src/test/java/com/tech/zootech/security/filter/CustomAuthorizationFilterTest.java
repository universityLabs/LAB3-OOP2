package com.tech.zootech.security.filter;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.tech.zootech.security.utils.contracts.AlgorithmUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.Request;

import org.apache.catalina.connector.Response;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

public class CustomAuthorizationFilterTest {
    /**
     * Method under test: {@link CustomAuthorizationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    public void testDoFilterInternal() throws ServletException, IOException {
        CustomAuthorizationFilter customAuthorizationFilter = new CustomAuthorizationFilter(mock(AlgorithmUtil.class));
        MockHttpServletRequest request = new MockHttpServletRequest();
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
        customAuthorizationFilter.doFilterInternal(request, response, filterChain);
        verify(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
    }

    /**
     * Method under test: {@link CustomAuthorizationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    public void testDoFilterInternal4() throws ServletException, IOException {
        CustomAuthorizationFilter customAuthorizationFilter = new CustomAuthorizationFilter(mock(AlgorithmUtil.class));
        MockHttpServletRequest request = new MockHttpServletRequest();
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doThrow(new ServletException("An error occurred")).when(filterChain)
                .doFilter((ServletRequest) any(), (ServletResponse) any());
        assertThrows(ServletException.class,
                () -> customAuthorizationFilter.doFilterInternal(request, response, filterChain));
        verify(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
    }
}

