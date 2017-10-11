package gr.pr.datereleases.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "RedirectFilter", value = "/RedirectFilter")
public class RedirectFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {


        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
