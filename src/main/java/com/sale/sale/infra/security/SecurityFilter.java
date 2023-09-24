package com.sale.sale.infra.security;

import com.sale.sale.infra.repositories.CustomerRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private  final TokenService tokenService;
    private final CustomerRepository customerRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = recoveryToken(request);
        if(token != null){
            var subject =tokenService.validToken(token);
            UserDetails userDetails = customerRepository.findByEmail(subject);
            var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }
        filterChain.doFilter(request, response);
    }

    private String recoveryToken(HttpServletRequest req){
        var authorization =  req.getHeader("Authorization");
        if(authorization == null) return null;
        return authorization.split(" ")[1];
    }
}
