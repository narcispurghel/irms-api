package com.irms.api.security;

import org.springframework.web.filter.OncePerRequestFilter;

public abstract class JwtFilter extends OncePerRequestFilter {
    protected abstract boolean isPublicPath(String path);
}
