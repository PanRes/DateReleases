package gr.pr.datereleases.utils;

import gr.pr.datereleases.hibernatetools.SeriesTools;
import gr.pr.datereleases.models.SeriesEpisodesModel;
import gr.pr.datereleases.models.SeriesModel;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class XlsxUtils {

    //need fix to check if series does not exist to insert it, add series from addDate, add notes on seriesEpisodes
    public static void readFromXlsx(File xlsxFile) throws IOException {
        List<SeriesEpisodesModel> seriesEpisodesModels = new ArrayList<>();

        FileInputStream fileInputStream = new FileInputStream(xlsxFile);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = xssfWorkbook.getSheetAt(0);
        Iterator<Row> rows = sheet.iterator();

        rows.next();
        while(rows.hasNext()) {
            Row row = rows.next();


            String series = row.getCell(0).getStringCellValue().trim();
            String seasonEpisode = row.getCell(3).getStringCellValue().trim();
            String notes = null;
            int season, episode;
            Date date = new Date(Calendar.getInstance().getTimeInMillis());
            if (row.getCell(6) != null) {
                notes = row.getCell(6).getStringCellValue();
            }

            if (seasonEpisode.length() < 6) {
                season = Integer.valueOf(seasonEpisode.substring(1, 3));
                episode = -1;
            } else {
                season = Integer.valueOf(seasonEpisode.substring(1, 3));
                episode = Integer.valueOf(seasonEpisode.substring(4,6));
            }

            if (row.getCell(4).getCellTypeEnum() == CellType.NUMERIC &&
                    DateUtil.isCellDateFormatted(row.getCell(4))) {
                date = new Date(row.getCell(4).getDateCellValue().getTime());
            } else {
                String dateString = row.getCell(4).getStringCellValue().trim();

                if (!dateString.equals("TBA")) {
                    if (dateString.substring(3, 5).equals("XX")) {
                        date.setDate(31);
                        date.setMonth(11);
                        date.setYear(Integer.valueOf(String.valueOf(20) + dateString.substring(6)));
                        if (notes == null) {
                            notes = "TBA 20" + dateString.substring(6);
                        }
                        else{
                            notes += ", TBA 20" + dateString.substring(6);
                        }
                    } else {
                        Calendar cal = Calendar.getInstance();
                        cal.set(Calendar.MONTH, Integer.valueOf(dateString.substring(3, 5)) - 1);
                        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                        date.setDate(maxDay);
                        date.setMonth(Integer.valueOf(dateString.substring(3, 5)) - 1);
                        date.setYear(Integer.valueOf(String.valueOf(20) + dateString.substring(6)));
                        if (notes == null) {
                            notes = "TBA 20" + dateString.substring(6);
                        }
                        else{
                            notes += ", TBA 20" + dateString.substring(6);
                        }
                    }
                }
            }
            SeriesModel seriesModel = SeriesTools.getSeriesByName(series);
            seriesEpisodesModels.add(new SeriesEpisodesModel());
            System.out.println(series + " | Season: " + season + " | Episode: " + episode + " | " + date + " | " + notes);
        }
    }
}
