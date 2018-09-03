package gr.pr.date_releases.servlets;

import gr.pr.date_releases.models.SeriesModel;
import gr.pr.date_releases.utils.GenericUtils;

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
		boolean ended = Integer.valueOf(request.getParameter("seriesEnded")) == 0;
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

		String seriesImgsDir = request.getServletContext().getInitParameter("seriesImgs");
		if(filePart.getSize() > 0){
			String imgUrl = request.getRealPath("") + seriesImgsDir;
			File imgFile = GenericUtils.uploadFile(filePart, new File(imgUrl),seriesName + "_");
			series.setImageUrl(seriesImgsDir + File.separator + imgFile.getName());
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
