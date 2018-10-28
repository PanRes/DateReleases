package gr.pr.date_releases.service;

import gr.pr.date_releases.dao.GenericDao;
import gr.pr.date_releases.dao.SeriesEpisodesDao;
import gr.pr.date_releases.entity.SeriesEpisodesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Repository
public class SeriesEpisodeServiceImpl implements SeriesEpisodeService {
	
	@Autowired
	private SeriesEpisodesDao seriesEpisodesDao;

	@Autowired
	private GenericDao genericDao;

	@Override
	@Transactional
	public SeriesEpisodesEntity getSeriesEpisodeById(int id) {
		return seriesEpisodesDao.getSeriesEpisodeById(id);
	}

	@Override
	@Transactional
	public void deleteSeriesEpisodeDate(int seriesEpisodeEntityId) {
		genericDao.delete(seriesEpisodesDao.getSeriesEpisodeById(seriesEpisodeEntityId));
	}

	@Override
	@Transactional
	public void saveOrUpdateSeriesEpisode(SeriesEpisodesEntity seriesEpisode){
		DateFormat format = new SimpleDateFormat("yyy-MM-dd", Locale.ENGLISH);
		Date date = null;
		try {
			date = format.parse("1900-01-01");
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		if (date != null && date.equals(seriesEpisode.getReleaseDate())) {
			seriesEpisode.setReleaseDate(null);
		}

		genericDao.saveOrUpdate(seriesEpisode);
	}
}
