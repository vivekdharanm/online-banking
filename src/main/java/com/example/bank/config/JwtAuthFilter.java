package com.example.bank.config;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.GenericFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtAuthFilter extends GenericFilter
{
	private final JwtUtil jwtUtil;
	
	public JwtAuthFilter(JwtUtil jwtUtil)
	{
		this.jwtUtil = jwtUtil;
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest request = (HttpServletRequest) req;
		String authHeader = request.getHeader("Authorizatuon");
		
		if(authHeader != null && authHeader.startsWith("Bearer"))
		{
			String token = authHeader.substring(7);
			try
			{
				var claims = jwtUtil.validateAndGetClaims(token);
				String email = claims.getSubject();
				String role = (String) claims.get("role");
				var auth = new UsernamePasswordAuthenticationToken(email, null, List.of(new SimpleGrantedAuthority(role)));
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
			catch (Exception e)
			{
				
			}
		}
		
		chain.doFilter(req, res);
	}
}
