package gr.pr.date_releases.service;

import gr.pr.date_releases.entity.SeriesTVChannel;

import java.util.List;

public interface ChannelService {
	
	List<SeriesTVChannel> getAllChannels();
	SeriesTVChannel createChannel(String channel);
	SeriesTVChannel getChannelById(int channelId);
}
