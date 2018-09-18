package gr.pr.date_releases.service;

import gr.pr.date_releases.dao.GenericDao;
import gr.pr.date_releases.dao.SeriesEpisodesDao;
import gr.pr.date_releases.entity.SeriesEpisodesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

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
		genericDao.deleteRow(seriesEpisodesDao.getSeriesEpisodeById(seriesEpisodeEntityId));
	}

	@Override
	@Transactional
	public void saveOrUpdateSeriesEpisode(SeriesEpisodesEntity seriesEpisodes, boolean editSeriesEpisode) {
		if (editSeriesEpisode) {
			genericDao.update(seriesEpisodes);
		}
		else {
			genericDao.save(seriesEpisodes);
		}
	}
}
