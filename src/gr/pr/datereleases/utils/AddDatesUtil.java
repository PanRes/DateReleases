package gr.pr.datereleases.utils;

import gr.pr.datereleases.hibernatetools.SeriesEpisodesTools;
import gr.pr.datereleases.hibernatetools.SeriesTools;
import gr.pr.datereleases.models.SeriesEpisodesModel;
import gr.pr.datereleases.models.SeriesModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddDatesUtil {

    public static boolean addSingleDate(HttpServletRequest request){

        int seriesId = Integer.valueOf(request.getParameter("seriesId"));
        int season = Integer.valueOf(request.getParameter("season"));
        int episode = Integer.valueOf(request.getParameter("episode"));
        String dateString = request.getParameter("date");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = new Date(sdf.parse(dateString).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SeriesModel series = SeriesTools.getSeriesById(seriesId);
        SeriesEpisodesModel seriesEpisodes = new SeriesEpisodesModel();

        seriesEpisodes.setSeriesBySeriesId(series);
        seriesEpisodes.setSeason(season);
        seriesEpisodes.setEpisode(episode);
        seriesEpisodes.setReleaseDate(date);

        try {
            SeriesEpisodesTools.insertSeriesEpisode(seriesEpisodes);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public static String addDatesFromXlsx(HttpServletRequest request) throws IOException, ServletException {
        System.out.println("addDatesFromXlsx start");
        java.util.Date currDate = new java.util.Date();
        String fileName = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String saveDir = "C:" + File.separator + "JavaTools" + File.separator + "uploadedFiles" +
                File.separator + sdf.format(currDate) + File.separator ;
        System.out.println(saveDir);
        File fileSaveDir = new File(saveDir);
        if(!fileSaveDir.exists()){
            fileSaveDir.mkdirs();
            System.out.println("dir does not exist");
        }

        for (Part part : request.getParts()) {
            fileName = extractFileName(part);
            System.out.println("fileName: " + fileName);
            fileName = saveDir + new File(fileName).getName();
            part.write(fileName);
            System.out.println(fileName);
        }

        return fileName;
    }


    public static String extractFileName(Part part){
        String contextDisp = part.getHeader("content-disposition");
        String[] items = contextDisp.split(";");
        for (String s : items) {
            if(s.trim().startsWith("filename")){
                System.out.println("extractFileName end");
                return s.substring(s.indexOf("=") + 2);
            }
        }
        System.out.println("extractFileName end");
        return "";
    }
}
