package ceu.dam.fct.api;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.Value;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

	@Value("${api.key}")
	private String apiKey;

	@Override
	protected void doFilterInternal(HttpServletRequest request, ServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String requestApiKey = request.getHeader("API-Key");

		if (requestApiKey == null || !requestApiKey.equals(apiKey)) {
			throw new ServletException("Invalid API Key");
		}

		filterChain.doFilter(request, response);
	}
}
