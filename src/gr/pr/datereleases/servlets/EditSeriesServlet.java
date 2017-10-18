package gr.pr.datereleases.servlets;

import gr.pr.datereleases.hibernatetools.HibernateTools;
import gr.pr.datereleases.hibernatetools.SeriesTools;
import gr.pr.datereleases.models.SeriesModel;
import gr.pr.datereleases.utils.GenericUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "EditSeriesServlet")
@MultipartConfig(fileSizeThreshold = 1024*1024*2,
                maxFileSize = 1024*1024*10,
                maxRequestSize = 1024*1024*50)
public class EditSeriesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int seriesId = Integer.valueOf(request.getParameter("seriesId"));
        String seriesName = request.getParameter("seriesName");
        String premiereDateString = request.getParameter("seriesPremiere");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date premiereDate = null;
        try {
            premiereDate = new Date(sdf.parse(premiereDateString).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String seriesChannel = request.getParameter("seriesChannel");

        Part filePart = request.getPart("imgUrl");
        byte ended = Byte.valueOf(request.getParameter("seriesEnded"));
        SeriesModel series = SeriesTools.getSeriesById(seriesId);
        if (seriesName != null && !seriesName.equals("")) {
            series.setName(seriesName);
        }

        if (premiereDate != null && !premiereDate.equals("")) {
            series.setDateStarted(premiereDate);
        }

        if(seriesChannel != null && !seriesChannel.equals("")){
            series.setChannel(seriesChannel);
        }


        if(filePart != null){
            String imgUrl = request.getRealPath("") + "contentFiles/imgs";
            File imgFile = GenericUtils.uploadFile(filePart, new File(imgUrl),seriesName + "_");
            series.setImageUrl("/contentFiles/imgs/" + imgFile.getName());
        }

        series.setEnded(ended);
        try{
            HibernateTools.updateEntity(series);
            request.setAttribute("successUpdate",true);
            request.getServletContext().getRequestDispatcher("/editSeries").forward(request,response);
        }
        catch (Exception e){
            e.printStackTrace();
            request.setAttribute("successUpdate",false);
            request.getServletContext().getRequestDispatcher("/editSeries").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("/");
    }
}
