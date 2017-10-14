package gr.pr.datereleases.utils;

import gr.pr.datereleases.hibernatetools.SeriesEpisodesTools;
import gr.pr.datereleases.hibernatetools.SeriesTools;
import gr.pr.datereleases.models.SeriesEpisodesModel;
import gr.pr.datereleases.models.SeriesModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class AddDatesUtil {

    public static boolean addSingleDate(HttpServletRequest request){

        int seriesId = Integer.valueOf(request.getParameter("seriesId"));
        int season = Integer.valueOf(request.getParameter("season"));
        int episode = Integer.valueOf(request.getParameter("episode"));
        String notes = request.getParameter("notes");
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
        seriesEpisodes.setNotes(notes);
        seriesEpisodes.setReleaseDate(date);

        try {
            SeriesEpisodesTools.insertSeriesEpisode(seriesEpisodes);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public static File addDatesFromXlsx(HttpServletRequest request) throws IOException, ServletException {
        java.util.Date currDate = new java.util.Date();
        Part filePart = request.getPart("uploadXlsx");
        String fileName = extractFileName(filePart);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String saveDir = "C:" + File.separator + "JavaTools" + File.separator + "uploadedFiles" +
                File.separator + sdf.format(currDate) + File.separator;
        File xlsxFile = new File(saveDir + File.separator + fileName);


        if(!xlsxFile.getParentFile().exists()){
            xlsxFile.getParentFile().mkdirs();
        }

        try {
            xlsxFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            OutputStream out = new FileOutputStream(xlsxFile);
            InputStream fileContent = filePart.getInputStream();
            int read = 0;
            byte[] bytes = new byte[1024];

            while((read = fileContent.read(bytes)) != -1){
                out.write(bytes,0,read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return xlsxFile;
    }


    public static String extractFileName(Part part){
        String contextDisp = part.getHeader("content-disposition");
        String[] items = contextDisp.split(";");
        for (String s : items) {
            if(s.trim().startsWith("filename")){
                return s.substring(s.indexOf("=") + 1).trim().replace("\"","");
            }
        }
        return "";
    }
}
