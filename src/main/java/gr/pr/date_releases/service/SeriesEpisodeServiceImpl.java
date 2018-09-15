package gr.pr.date_releases.service;

import gr.pr.date_releases.dao.SeriesEpisodesDao;
import gr.pr.date_releases.entity.SeriesEpisodesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SeriesEpisodeServiceImpl implements SeriesEpisodeService {
	
	@Autowired
	private SeriesEpisodesDao seriesEpisodesDao;
	
	@Override
	public SeriesEpisodesEntity getSeriesEpisodeById(int id) {
		return seriesEpisodesDao.getSeriesEpisodeById(id);
	}
}
