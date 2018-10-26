package gr.pr.date_releases.controllers;

import gr.pr.date_releases.entity.SeriesEntity;
import gr.pr.date_releases.entity.SeriesEpisodesEntity;
import gr.pr.date_releases.entity.SeriesTVChannel;
import gr.pr.date_releases.entity.VideoType;
import gr.pr.date_releases.service.ChannelService;
import gr.pr.date_releases.service.SeriesEpisodeService;
import gr.pr.date_releases.service.SeriesService;
import gr.pr.date_releases.service.VideoTypeService;
import org.hibernate.AssertionFailure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

@Controller
@SessionAttributes({"series", "seriesEpisode"})
@RequestMapping("/series")
public class SeriesController {

	@Autowired
	private SeriesService seriesService;

	@Autowired
	private SeriesEpisodeService seriesEpisodeService;

	@Autowired
	private ChannelService channelService;

	@Autowired
	private VideoTypeService videoTypeService;

	@RequestMapping
	public String seeAllSeries(Model model) {

		model.addAttribute("viewAllSeries", true);

		return "mainPages/seriesPages/series";
	}

	@RequestMapping("/addSeries")
	public String addSeries(Model model) {

		SeriesEntity series = new SeriesEntity();
		List<SeriesTVChannel> channels = channelService.getAllChannels();
		List<VideoType> videoTypes = videoTypeService.getAllVideoTypes();

		model.addAttribute("series", series);
		model.addAttribute("channels", channels);
		model.addAttribute("videoTypes", videoTypes);

		return "mainPages/seriesPages/addEditSeries";
	}

	@RequestMapping("/edit/{seriesName}")
	public String editSeries(@PathVariable("seriesName") String seriesName, Model model) {

		SeriesEntity series = seriesService.getSeriesBySeriesName(seriesName);
		List<SeriesTVChannel> channels = channelService.getAllChannels();
		List<VideoType> videoTypes = videoTypeService.getAllVideoTypes();

		model.addAttribute("series", series);
		model.addAttribute("channels", channels);
		model.addAttribute("videoTypes", videoTypes);

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
		now.add(Calendar.DATE, -1);
		/*Because US series displayed at the night of that day,
		 *I sub one day from now for released label
		 * */

		model.addAttribute("seriesEpisodes", seriesEpisodes);
		model.addAttribute("now", new Date(now.getTimeInMillis()));

		return "mainPages/seriesPages/viewSeriesSchedule";
	}

	@RequestMapping("/info/{seriesName}")
	public String seriesInfo(@PathVariable("seriesName") String seriesName,
							 @RequestParam(name = "episodeSaved", required = false) String episodeSaved,
							 Model model) {

		SeriesEntity series = seriesService.getSeriesBySeriesName(seriesName);
		model.addAttribute("series", series);
		model.addAttribute("seriesInfo", true);

		if (episodeSaved != null) {
			model.addAttribute("episodeSaved", true);
		}

		return "mainPages/seriesPages/seriesInfo";
	}

	@PostMapping("/saveOrUpdateSeries")
	public String saveOrUpdateSeries(@ModelAttribute("series") SeriesEntity series, Model model,
									 @RequestParam("tvChannel") int tvChannelId,
									 @RequestParam("videoType") int videoTypeId,
									 HttpServletRequest request) {

		String error = "";

		try {
			if (tvChannelId != 0) {
				series.setChannel(channelService.getChannelById(tvChannelId));
			}
			series.setVideoType(videoTypeService.getVideoTypeById(videoTypeId));
			seriesService.saveOrUpdateSeries(series);
			return "redirect:/series/info/" + series.getName();
		}
		catch (AssertionFailure e) {
			e.printStackTrace();
			error = "duplicateName";
		}
		catch (Exception e) {
			e.printStackTrace();
			error = "genericError";
		}

		if (request.getHeader("Referer").contains("?") && !request.getHeader("Referer").contains(error)) {
			return "redirect:" + request.getHeader("Referer") + "&" + error;
		}
		else {
			return "redirect:" + request.getHeader("Referer") + "?" + error;
		}
	}

	@RequestMapping("/saveOrUpdateSeriesEpisode")
	public String saveOrUpdateSeriesEpisode(@ModelAttribute("seriesEpisode") SeriesEpisodesEntity seriesEpisode,
											@RequestParam("editSeriesEpisode") boolean editSeriesEpisode,
											Model model) {

		//FIXME : handle when episode already exists and try to add it
		seriesEpisode.setSeries(seriesService.getSeriesBySeriesName(seriesEpisode.getSeries().getName()));
		seriesEpisodeService.saveOrUpdateSeriesEpisode(seriesEpisode, editSeriesEpisode);
		model.addAttribute("episodeSaved", true);

		return "redirect:/series/info/" + seriesEpisode.getSeries().getName();
	}

	@GetMapping("/addSeriesToUserFavorites")
	public String addSeriesToUserFavorites(@RequestParam("seriesId") int seriesId, HttpServletRequest request) {

		seriesService.addSeriesToUserFavorites(seriesId);

		return "redirect:" + request.getHeader("Referer");
	}

	@GetMapping("/removeSeriesToUserFavorites")
	public String removeSeriesToUserFavorites(@RequestParam("seriesId") int seriesId, HttpServletRequest request) {

		seriesService.removeSeriesToUserFavorites(seriesId);

		return "redirect:" + request.getHeader("Referer");
	}

}
