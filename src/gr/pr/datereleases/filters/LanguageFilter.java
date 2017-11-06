package gr.pr.datereleases.filters;

import com.sun.deploy.net.HttpRequest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "LanguageFilter", value = "/LanguageFilter")
public class LanguageFilter implements Filter{
	public void destroy(){
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException{

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		String querySting = httpRequest.getQueryString();



		chain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException{

	}

}
