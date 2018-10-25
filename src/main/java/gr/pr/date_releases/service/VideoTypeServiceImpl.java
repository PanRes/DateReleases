package gr.pr.date_releases.service;

import gr.pr.date_releases.dao.VideoTypeDao;
import gr.pr.date_releases.entity.VideoType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoTypeServiceImpl implements VideoTypeService {
	
	@Autowired
	private VideoTypeDao videoTypeDao;
	
	public List<VideoType> getAllVideoTypes() {
		return videoTypeDao.getAllVideoTypes();
	}
	
	@Override
	public VideoType getVideoTypeById(int videoTypeId) {
		return videoTypeDao.getVideoTypeById(videoTypeId);
	}
}
