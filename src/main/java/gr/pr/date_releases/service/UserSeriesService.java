package gr.pr.date_releases.service;

import gr.pr.date_releases.entity.SeriesEntity;

import javax.transaction.Transactional;

public interface UserSeriesService {
	
	@Transactional
	void removeUserFavoriteByUserName(SeriesEntity series, String userName);
}
