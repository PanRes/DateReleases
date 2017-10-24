package gr.pr.datereleases.servlets;

import gr.pr.datereleases.hibernatetools.HibernateTools;
import gr.pr.datereleases.hibernatetools.SeriesTools;
import gr.pr.datereleases.models.SeriesModel;
import gr.pr.datereleases.utils.GenericUtils;
import org.hibernate.HibernateException;

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

@WebServlet(name = "AddSeriesServlet", value = "/AddSeriesServlet")
@MultipartConfig(fileSizeThreshold = 1024*1024*2,
                maxFileSize = 1024*1024*10,
                maxRequestSize = 1024*1024*50)
public class AddSeriesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String seriesName = request.getParameter("seriesName");
        try {
            SeriesModel series = SeriesTools.getSeriesByName(seriesName);
            response.sendRedirect("/addSeries?successCreation='exists'");
        }
        catch (HibernateException he){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date premiereDate = null;
            try {
                premiereDate = new Date(sdf.parse(request.getParameter("seriesPremiere")).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String seriesChannel = request.getParameter("seriesChannel");
            boolean ended = Integer.valueOf(request.getParameter("seriesEnded")) == 0;

            Part filePart = request.getPart("imgUrl");
            SeriesModel series = new SeriesModel();
            series.setName(seriesName);
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
                HibernateTools.insertEntity(series);
                response.sendRedirect("/addSeries?successCreation='success'");
            }
            catch (Exception e){
                e.printStackTrace();
                response.sendRedirect("/addSeries?successCreation='fail'");
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("/");
    }
}