package com.bookyourhotel.configuration;

import com.bookyourhotel.entity.AppUserEntity;
import com.bookyourhotel.repository.AppUserRepository;
import com.bookyourhotel.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class JwtConfig extends OncePerRequestFilter
{
    private JwtService js;
    private AppUserRepository aur;

    public JwtConfig(JwtService js, AppUserRepository aur) {
        this.js = js;
        this.aur = aur;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        String tokenHeader = request.getHeader("Authorization");
        if (tokenHeader != null && tokenHeader.startsWith("Bearer "))
        {
            String token = tokenHeader.substring(8, tokenHeader.length() - 1);
            String username = js.getUserName(token);
            Optional<AppUserEntity> userName = aur.findByUsername(username);
            if (userName.isPresent()) {
                AppUserEntity opUser = userName.get();
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(opUser, null, Collections.singleton(new SimpleGrantedAuthority(opUser.getRole())));
                authentication.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
