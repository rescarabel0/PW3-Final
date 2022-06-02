package br.edu.aluno.projetofinal.auth;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AuthFilter<T> extends OncePerRequestFilter {
    private static final String authorizationKey = "Authorization";
    private static final String bearerKey = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                var token = parseToken(request);
                if (token != null) {
                    authenticate(token, request);
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        filterChain.doFilter(request, response);
    }

    @Nullable
    private String parseToken(@NonNull HttpServletRequest request) {
        String headerAuth = request.getHeader(authorizationKey);
        return (StringUtils.hasText(headerAuth) && headerAuth.startsWith(bearerKey)) ?
                headerAuth.substring(bearerKey.length()) :
                headerAuth;
    }

    private void authenticate(@NonNull String token, @NonNull HttpServletRequest request) {
        var tokenData = validateToken(token);
        if (tokenData == null)
            return;

        var authentication = getAuthentication(tokenData, request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Nullable
    protected abstract T validateToken(@NonNull String token);

    @Nullable
    protected abstract Authentication getAuthentication(@NonNull T tokenData, @NonNull HttpServletRequest request);
}
