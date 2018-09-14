package gr.pr.date_releases.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddDateServlet", value = "/AddDateServlet")
@MultipartConfig(fileSizeThreshold = 1024*1024*2,
				maxFileSize = 1024*1024*10,
				maxRequestSize = 1024*1024*50)
public class AddDateServlet extends HttpServlet {
	/*protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/addSeriesDate");

	}
}
