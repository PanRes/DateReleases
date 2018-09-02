package gr.pr.date_releases.servlets;

import gr.pr.date_releases.hibernatetools.HibernateTools;
import gr.pr.date_releases.hibernatetools.SeriesEpisodesTools;
import gr.pr.date_releases.models.SeriesEpisodesModel;
import gr.pr.date_releases.utils.AddDatesUtil;
import gr.pr.date_releases.utils.GenericUtils;
import gr.pr.date_releases.utils.XlsxUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "AddDateServlet", value = "/AddDateServlet")
@MultipartConfig(fileSizeThreshold = 1024*1024*2,
				maxFileSize = 1024*1024*10,
				maxRequestSize = 1024*1024*50)
public class AddDateServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean success = false;
		String redirect = "/addSeriesDate" + "?success=";

		String formName = request.getParameter("formName");

		if(formName.equals("frmAddDateManually")){
			success = AddDatesUtil.addSingleDate(request);
		}
		else if(formName.equals("frmAddDatesWithXlsx")){
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Part filePart = request.getPart("uploadXlsx");

			String saveDir = request.getRealPath("") + "contentFiles/xlsxFiles" +
					File.separator + sdf.format(now);
			sdf = new SimpleDateFormat("yyyy-MM-dd_hh.mm.ss ");
			File xlsxFile = null;
			try {
				xlsxFile = GenericUtils.uploadFile(filePart, new File(saveDir), sdf.format(now));
				List<SeriesEpisodesModel> seriesEpisodes = XlsxUtils.readFromXlsx(xlsxFile);
				SeriesEpisodesTools.insertMultipleSeriesEpisodes(seriesEpisodes);
				success = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (formName.equals("frmEditDate")){
			int seriesEpisodeId = Integer.valueOf(request.getParameter("seriesEpisodeId"));
			String dateString = request.getParameter("date");
			redirect = "/editSeriesDate?seriesEpisodeId=" + seriesEpisodeId + "&success=";

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.sql.Date date = null;
			try {
				date = new java.sql.Date(sdf.parse(dateString).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String notes = request.getParameter("notes");

			SeriesEpisodesModel seriesEpisode = SeriesEpisodesTools.getSeriesEpisodeById(seriesEpisodeId);
			seriesEpisode.setNotes(notes);
			seriesEpisode.setReleaseDate(date);

			try{
				HibernateTools.updateEntity(seriesEpisode);
				success = true;
			}catch (Exception e){
				e.printStackTrace();
			}
		}

		response.sendRedirect(redirect + success);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/addSeriesDate");

	}
}
