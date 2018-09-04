package gr.pr.date_releases.service;

import gr.pr.date_releases.dao.SeriesDao;
import gr.pr.date_releases.entity.SeriesEntity;
import gr.pr.date_releases.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SeriesService {

	@Autowired
	private SeriesDao seriesDao;
	
	
}
