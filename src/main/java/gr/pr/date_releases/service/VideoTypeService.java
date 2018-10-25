package gr.pr.date_releases.service;

import gr.pr.date_releases.entity.VideoType;

import java.util.List;

public interface VideoTypeService {

	List<VideoType> getAllVideoTypes();
	VideoType getVideoTypeById(int videoTypeId);
}
