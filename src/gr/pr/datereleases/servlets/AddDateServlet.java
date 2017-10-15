package gr.pr.datereleases.servlets;

import gr.pr.datereleases.hibernatetools.SeriesEpisodesTools;
import gr.pr.datereleases.models.SeriesEpisodesModel;
import gr.pr.datereleases.utils.AddDatesUtil;
import gr.pr.datereleases.utils.GenericUtils;
import gr.pr.datereleases.utils.XlsxUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddDateServlet", value = "/AddDateServlet")
@MultipartConfig(fileSizeThreshold = 1024*1024*2,
                maxFileSize = 1024*1024*10,
                maxRequestSize = 1024*1024*50)
public class AddDateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean success = false;


        String formName = request.getParameter("formName");

        if(formName != null && formName.equals("frmAddDateManually")){
            success = AddDatesUtil.addSingleDate(request);
        }
        else if(formName.equals("frmAddDatesWithXlsx")){
            File xlsxFile = null;
            try {
                xlsxFile = GenericUtils.uploadFile(request);
                List<SeriesEpisodesModel> seriesEpisodes = XlsxUtils.readFromXlsx(xlsxFile);
                SeriesEpisodesTools.insertMultipleSeriesEpisodes(seriesEpisodes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        request.setAttribute("success",success);
        request.getSession().setAttribute("success","true");
        response.sendRedirect("/addDate");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("/addDate");

    }
}
