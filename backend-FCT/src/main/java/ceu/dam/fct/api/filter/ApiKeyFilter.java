package ceu.dam.fct.api.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@SecurityScheme(name = "Authorization", type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER, paramName = "API-KEY")
public class ApiKeyFilter extends OncePerRequestFilter {

	@Value("${api.key}")
	private String apiKey;

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		String requestURI = request.getRequestURI();
		return (requestURI.startsWith("/swagger") || requestURI.startsWith("/docs"));
	}

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String apiKeyRequest = request.getHeader("API-KEY");

		if (apiKey.equals(apiKeyRequest)) {
			filterChain.doFilter(request, response);
		} else {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.getWriter().write("No es el filtro que se pide!!");
		}
	}
}
