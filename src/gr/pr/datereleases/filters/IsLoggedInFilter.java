package gr.pr.datereleases.filters;

import gr.pr.datereleases.models.UsersModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*
* Not used
* */

@WebFilter(filterName = "IsLoggedInFilter", value = "/IsLoggedInFilter")
public class IsLoggedInFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        UsersModel user = (UsersModel) session.getAttribute("user");

        if(user != null){
            resp.sendRedirect("/");
        }
        else{
            chain.doFilter(request, response);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
