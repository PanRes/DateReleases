package gr.pr.date_releases.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/series")
public class SeriesController {
	
	@RequestMapping("/allSeries")
	public String seeAllSeries() {
		return "mainPages/seriesPages/series";
	}
	
	@RequestMapping(value = {"/addSeries","/{seriesName}/editSeries"})
	public String addSeries(@PathVariable("seriesName") String seriesName) {
		return "mainPages/seriesPages/addSeries";
	}
	
	@RequestMapping(value = {"/addSeriesDate","/editSeriesDate"})
	public String addSeriesDate() {
		return "mainPages/seriesPages/addSeriesDate";
	}
	
	@RequestMapping("/schedule")
	public String viewSeriesSchedule() {
		return "mainPages/seriesPages/viewSeriesSchedule";
	}
	
	@RequestMapping("/{seriesName}/info")
	public String seriesInfo(@PathVariable("seriesName") String seriesName) {
		return "mainPages/seriesPages/seriesInfo";
	}
	
}
