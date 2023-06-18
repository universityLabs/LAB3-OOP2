package com.tech.zootech.security.security;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {ApplicationConfig.class, AuthenticationConfiguration.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicationConfigTest {
    @Autowired
    private ApplicationConfig applicationConfig;

    @MockBean
    private UserDetailsService userDetailsService;

    /**
     * Method under test: {@link ApplicationConfig#passwordEncoder()}
     */
    @Test
    public void testPasswordEncoder() {
        assertTrue(ApplicationConfig.passwordEncoder() instanceof BCryptPasswordEncoder);
    }

    /**
     * Method under test: {@link ApplicationConfig#authenticationManager(AuthenticationConfiguration)}
     */
    @Test
    public void testAuthenticationManager() throws Exception {
        assertTrue(applicationConfig.authenticationManager(new AuthenticationConfiguration()) instanceof ProviderManager);
    }

    /**
     * Method under test: {@link ApplicationConfig#authenticationManager(AuthenticationConfiguration)}
     */
    @Test
    public void testAuthenticationManager2() throws Exception {
        AuthenticationConfiguration authenticationConfiguration = new AuthenticationConfiguration();
        authenticationConfiguration.setApplicationContext(mock(AnnotationConfigApplicationContext.class));
        assertTrue(applicationConfig.authenticationManager(authenticationConfiguration) instanceof ProviderManager);
    }

    /**
     * Method under test: {@link ApplicationConfig#authenticationProvider()}
     */
    @Test
    public void testAuthenticationProvider() {
        assertTrue(applicationConfig.authenticationProvider() instanceof DaoAuthenticationProvider);
    }
}

