package gr.pr.date_releases.controllers;

import gr.pr.date_releases.entity.SeriesEntity;
import gr.pr.date_releases.entity.SeriesEpisodesEntity;
import gr.pr.date_releases.entity.UserEntity;
import gr.pr.date_releases.service.SeriesService;
import gr.pr.date_releases.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/series")
public class SeriesController {
	
	@Autowired
	private SeriesService seriesService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String seeAllSeries() {
		return "mainPages/seriesPages/series";
	}
	
	@RequestMapping("/addSeries")
	public String addSeries(Model model) {
		
		SeriesEntity series = new SeriesEntity();
		
		model.addAttribute("series", series);
		
		return "mainPages/seriesPages/addSeries";
	}
	
	@RequestMapping("/{seriesName}/editSeries")
	public String editSeries(@PathVariable("seriesName") String seriesName, Model model) {
		
		SeriesEntity series = seriesService.getSeriesBySeriesName(seriesName);
		
		model.addAttribute("series", series);
		
		return "mainPages/seriesPages/addSeries";
	}
	
	@RequestMapping("{seriesName}/addSeriesDate")
	public String addSeriesDate(@PathVariable("seriesName") String seriesName, Model model) {
		
		SeriesEntity series = seriesService.getSeriesBySeriesName(seriesName);
		SeriesEpisodesEntity seriesEpisodes = new SeriesEpisodesEntity();
		model.addAttribute("series", series);
		model.addAttribute("seriesEpisodes", seriesEpisodes);
		
		return "mainPages/seriesPages/addSeriesDate";
	}
	
	@RequestMapping("/editSeriesDate/{seriesName}/{seriesEpisode}")
	public String editSeriesDate(@PathVariable("seriesName") String name,
								 @PathVariable("seriesEpisode") String seriesEpisodeString, Model model) {
		
/*
		SeriesEpisodesEntity =
*/
		
		return "";
	}
	
	@RequestMapping(value = {"/schedule", "/schedule/{seriesName}"})
	public String viewSeriesSchedule(@PathVariable("seriesName") String name, Model model) {
		
		SeriesEntity series = seriesService.getSeriesBySeriesName(name);
		//TODO : check validation
		if (series != null) {
			model.addAttribute("series",series);
		}
		
		return "mainPages/seriesPages/viewSeriesSchedule";
	}
	
	@RequestMapping("/schedule/favorites")
	public String viewFavoritesSeries(Model model) {
		
		UserEntity userLogged = userService.getLoggedInUser();
		model.addAttribute("series", userLogged.getFavoriteSeries());
		
		return "mainPages/seriesPages/viewSeriesSchedule";
	}
	
	@RequestMapping("/info/{seriesName}")
	public String seriesInfo(@PathVariable("seriesName") String seriesName, Model model) {
		
		SeriesEntity series = seriesService.getSeriesBySeriesName(seriesName);
		model.addAttribute("series", series);
		
		return "mainPages/seriesPages/seriesInfo";
	}
	
	//TODO : check if parameters are returned with post request
	@PostMapping(value = "/saverOrUpdateSeries", consumes = {"multipart/form-data"})
	public String saveOrUpdateSeries(@ModelAttribute("series") SeriesEntity series, Model model,
			@RequestParam("imgUrl") MultipartFile multipart) {
		
		//TODO : check if upload fails
		String imgUrl = seriesService.uploadImgUrl(multipart, series.getName());
		series.setImageUrl(imgUrl);
		
		//TODO : show success or fail message in Series info when redirect
		try {
			seriesService.saveOrUpdateSeries(series);
			model.addAttribute("success",true);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("success",false);
		}
		
		return "/{" + series.getName() + "}/info";
	}
	
	@PostMapping("/saveOrUpdateSeriesEpisode")
	public String saveOrUpdateSeriesEpisode(@ModelAttribute("seriesEpisode") SeriesEpisodesEntity seriesEpisode, Model model) {
		
		return "";
	}
	
}
