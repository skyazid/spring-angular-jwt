package com.example.jwt.config.security.jwt.filter;

import com.example.jwt.config.security.jwt.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Override
    @SuppressWarnings("unchecked")
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (httpServletRequest.getMethod().equals(HttpMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        } else {
            String jwt = httpServletRequest.getHeader(SecurityConstants.AUTHORIZATION_HEADER);
            if (jwt == null || !jwt.startsWith(SecurityConstants.TOKEN_PREFIX)) {
                filterChain.doFilter(httpServletRequest, httpServletResponse);
                return;
            }
            Claims claims = Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET)
                    .parseClaimsJws(jwt.replace(SecurityConstants.TOKEN_PREFIX, ""))
                    .getBody();
            String username = claims.getSubject();
            List<Map<String, String>> roles = (List<Map<String, String>>) claims.get("roles");
            List<GrantedAuthority> authorities = roles
                    .stream()
                    .map(r -> new SimpleGrantedAuthority(r.get("authority")))
                    .collect(Collectors.toList());
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

}
