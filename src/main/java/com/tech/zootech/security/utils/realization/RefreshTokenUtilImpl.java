package com.tech.zootech.security.utils.realization;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.zootech.security.DTO.UserDto;
import com.tech.zootech.security.domain.Role;
import com.tech.zootech.security.exceptions.RefreshTokenException;
import com.tech.zootech.security.service.UserService;
import com.tech.zootech.security.utils.contracts.AlgorithmUtil;
import com.tech.zootech.security.utils.contracts.RefreshTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service("userDefaultImpl")
@RequiredArgsConstructor
public class RefreshTokenUtilImpl implements RefreshTokenUtil {
    public static final int TEN_MINUTES_REFRESH_TIME = 10 * 60 * 1000;
    public static final String AUTH_SUBSTR = "Bearer ";
    public static final String HEADER_NAME = "error";
    public static final String ERROR_MESSAGE = "error_message";
    public static final String REFRESH_TOKEN_IS_MISSING = "Refresh token is missing";
    private final UserService userService;
    private final AlgorithmUtil algorithmUtil;

    @Override
    public void refresh(HttpServletRequest request,
                        HttpServletResponse response) throws IOException {
        var authorizationHeader = request.getHeader(AUTHORIZATION);
        if (!(authorizationHeader != null && authorizationHeader.startsWith(AUTH_SUBSTR))) {
            throw new RefreshTokenException(REFRESH_TOKEN_IS_MISSING);
        }
        else {
            try {
                var refreshToken = authorizationHeader.substring(AUTH_SUBSTR.length());
                var algorithm = algorithmUtil.getAlgorithm();
                var verifier = JWT.require(algorithm).build();
                var decodeJWT = verifier.verify(refreshToken);
                var username = decodeJWT.getSubject();
                var user = userService.getUserByUsername(username);
                String accessToken = generateAccessToken(request, algorithm, user);
                HashMap<Object, Object> tokens = getTokensMap(response, refreshToken, accessToken);
                writeValues(response, tokens);
            } catch (Exception exception) {
                configureResponse(response, exception);
                HashMap<Object, Object> error = configureErrorMap(ERROR_MESSAGE, exception.getMessage());
                writeValues(response, error);
            }
        }
    }

    private static HashMap<Object, Object> configureErrorMap(String errorMessage, String exception) {
        var error = new HashMap<>();
        error.put(errorMessage, exception);
        return error;
    }

    private static void configureResponse(HttpServletResponse response, Exception exception) {
        response.setHeader(HEADER_NAME, exception.getMessage());
        response.setStatus(FORBIDDEN.value());
    }

    private static void writeValues(HttpServletResponse response, HashMap<Object, Object> tokens) throws IOException {
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }

    private static HashMap<Object, Object> getTokensMap(HttpServletResponse response, String refreshToken, String accessToken) {
        HashMap<Object, Object> tokens = configureErrorMap("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);
        response.setContentType(APPLICATION_JSON_VALUE);
        return tokens;
    }

    private static String generateAccessToken(HttpServletRequest request, Algorithm algorithm, UserDto user) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date((System.currentTimeMillis() + TEN_MINUTES_REFRESH_TIME)))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getRoles().stream().map(Role::getName).toList())
                .sign(algorithm);
    }
}
