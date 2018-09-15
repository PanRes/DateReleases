package gr.pr.date_releases.controllers.advice;

import gr.pr.date_releases.entity.SeriesEntity;
import gr.pr.date_releases.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class GlobalControllerAdvice {

	@Autowired
	private SeriesService seriesService;

	@ModelAttribute("allSeries")
	public List<SeriesEntity> allSeries() {
		return seriesService.getAllSeries();
	}
}
