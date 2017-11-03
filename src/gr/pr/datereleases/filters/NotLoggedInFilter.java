package gr.pr.datereleases.filters;

import gr.pr.datereleases.models.UsersModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebFilter(filterName = "NotLoggedInFilter", value = "/NotLoggedInFilter"/*,
            servletNames = {"mainMenu","addDate","viewSchedule","AddDateServlet","ScheduleServlet"},
            urlPatterns = {"/jsps/mainPages/*","/jsps/universals/*","/jsps/util/*"}*/)
public class NotLoggedInFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;
        String servletPath = req.getServletPath();
        if (req.getQueryString() != null){
            servletPath += "?" + req.getQueryString();
        }
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        UsersModel user = (UsersModel) session.getAttribute("user");
        if(user == null){
            req.setAttribute("page", servletPath);
            req.getServletContext().getRequestDispatcher("/login").forward(req,resp);
        }
        else {
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
