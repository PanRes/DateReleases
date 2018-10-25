package gr.pr.date_releases.service;

import gr.pr.date_releases.entity.SeriesTVChannel;

import java.util.List;

public interface ChannelService {
	
	List<SeriesTVChannel> getAllChannels();
	
	SeriesTVChannel getChannelById(int channelId);
}
