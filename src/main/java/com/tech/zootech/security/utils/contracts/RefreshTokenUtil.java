package com.tech.zootech.security.utils.contracts;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface RefreshTokenUtil {
    void refresh(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
