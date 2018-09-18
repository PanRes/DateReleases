package gr.pr.date_releases.controllers;

import gr.pr.date_releases.entity.SeriesEntity;
import gr.pr.date_releases.entity.SeriesEpisodesEntity;
import gr.pr.date_releases.service.SeriesEpisodeService;
import gr.pr.date_releases.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/series")
public class SeriesController {

	@Autowired
	private SeriesService seriesService;
	
	@Autowired
	private SeriesEpisodeService seriesEpisodeService;

	@RequestMapping
	public String seeAllSeries(Model model) {

		model.addAttribute("viewAllSeries", true);

		return "mainPages/seriesPages/series";
	}
	
	@RequestMapping("/addSeries")
	public String addSeries(Model model) {
		
		SeriesEntity series = new SeriesEntity();
		
		model.addAttribute("series", series);
		
		return "mainPages/seriesPages/addEditSeries";
	}
	
	@RequestMapping("/{seriesName}/editSeries")
	public String editSeries(@PathVariable("seriesName") String seriesName, Model model) {
		
		SeriesEntity series = seriesService.getSeriesBySeriesName(seriesName);
		
		model.addAttribute("series", series);
		
		return "mainPages/seriesPages/addEditSeries";
	}
	
	@RequestMapping("/addSeriesEpisodeDate")
	public String addSeriesDate(Model model) {

		//TODO : have the possibility to add episode for specific series
		SeriesEpisodesEntity seriesEpisode = new SeriesEpisodesEntity();
		model.addAttribute("seriesEpisode", seriesEpisode);
		
		return "mainPages/seriesPages/addEditSeriesEpisodeDate";
	}
	
	@RequestMapping("/editSeriesEpisodeDate")
	public String editSeriesDate(@RequestParam("seriesEpisodeId") int id, Model model) {
		
		SeriesEpisodesEntity seriesEpisode = seriesEpisodeService.getSeriesEpisodeById(id);
		model.addAttribute("seriesEpisode", seriesEpisode);
		
		return "mainPages/seriesPages/addEditSeriesEpisodeDate";
	}
	
	
	
	@RequestMapping("/deleteSeriesEpisodeDate")
	public String deleteSeriesEpisodeDate(@RequestParam("seriesEpisodeId") int id,
								 HttpServletRequest request) {
		
		seriesEpisodeService.deleteSeriesEpisodeDate(id);
		
		//TODO : edit url
		return "redirect:" + request.getHeader("Referer");
	}
	
	

	@RequestMapping(value = {"/schedule"})
	public String viewSeriesSchedule(@RequestParam("series") String seriesName, Model model) {
		
		List<SeriesEpisodesEntity> seriesEpisodes = seriesService.getSeriesEpisodes(seriesName);
		
		
		Calendar now = Calendar.getInstance(Locale.US);
		now.add(Calendar.DATE,-1);
		/*Because US series displayed at the night of that day,
		 *I sub one day from now for released label
		 * */
		
		model.addAttribute("seriesEpisodes", seriesEpisodes);
		model.addAttribute("now", new Date(now.getTimeInMillis()));
		
		return "mainPages/seriesPages/viewSeriesSchedule";
	}

	@RequestMapping("/{seriesName}")
	public String seriesInfo(@PathVariable("seriesName") String seriesName,
							 @RequestParam(name = "episodeSaved", required = false) String episodeSaved,
							 Model model) {
		
		SeriesEntity series = seriesService.getSeriesBySeriesName(seriesName);
		model.addAttribute("series", series);
		model.addAttribute("seriesInfo", true);

		if  (episodeSaved != null) {
			model.addAttribute("episodeSaved", true);
		}

		return "mainPages/seriesPages/seriesInfo";
	}
	
	//TODO : check if parameters are returned with post request
	@PostMapping(value = "/saverOrUpdateSeries", consumes = {"multipart/form-data"})
	public String saveOrUpdateSeries(@ModelAttribute("series") SeriesEntity series, Model model,
			@RequestParam(name = "imgUrl", required = false) MultipartFile multipart) {
		
		if (multipart != null) {
			//TODO : check if upload fails
			String imgUrl = seriesService.uploadImgUrl(multipart, series.getName());
			series.setImageUrl(imgUrl);
		}
		
		//TODO : show success or fail message in Series info when redirect
		try {
			seriesService.saveOrUpdateSeries(series);
			model.addAttribute("success",true);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("success",false);
		}
		
		return "/series/" + series.getName();
	}
	
	@PostMapping("/saveOrUpdateSeriesEpisode")
	public String saveOrUpdateSeriesEpisode(@ModelAttribute("seriesEpisode") SeriesEpisodesEntity seriesEpisode,
											@RequestParam("editSeriesEpisode") boolean editSeriesEpisode) {

		seriesEpisode.setSeries(seriesService.getSeriesBySeriesName(seriesEpisode.getSeries().getName()));
		seriesEpisodeService.saveOrUpdateSeriesEpisode(seriesEpisode, editSeriesEpisode);

		return "/series/" + seriesEpisode.getSeries().getName() + "?episodeSaved";
	}
	
	@GetMapping("/addSeriesToUserFavorites")
	public String addSeriesToUserFavorites(@RequestParam("seriesName") String seriesName) {
		
		seriesService.addSeriesToUserFavorites(seriesName);
		
		return "/" + seriesName;
	}
	
	@GetMapping("/removeSeriesToUserFavorites")
	public String removeSeriesToUserFavorites(@RequestParam("seriesName") String seriesName) {
		
		seriesService.removeSeriesToUserFavorites(seriesName);
		
		return "/" + seriesName;
	}
	
}
