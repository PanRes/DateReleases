package gr.pr.datereleases.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "notLoggedInFilter", value = "/notLoggedInFilter"/*,
            servletNames = {"mainMenu","addDate","viewSchedule","AddDateServlet","ScheduleServlet"},
            urlPatterns = {"/jsps/mainPages/*","/jsps/universals/*","/jsps/util/*"}*/)
public class notLoggedInFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;
        String servletPath = req.getServletPath();
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");

        if(user == null){
            req.setAttribute("page", servletPath);
            req.getServletContext().getRequestDispatcher("/").forward(req,resp);
        }
        else {
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
