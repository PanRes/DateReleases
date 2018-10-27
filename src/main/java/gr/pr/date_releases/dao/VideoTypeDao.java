package gr.pr.date_releases.dao;

import gr.pr.date_releases.entity.VideoType;

import java.util.List;

public interface VideoTypeDao {
	
	List<VideoType> getAllVideoTypes();
	VideoType getVideoTypeById(int videoTypeId);
}
