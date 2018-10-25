package gr.pr.date_releases.dao;

import gr.pr.date_releases.entity.SeriesTVChannel;

import java.util.List;

public interface ChannelDao {
	
	List<SeriesTVChannel> getAllChannels();
	SeriesTVChannel getChannelById(int channelId);
}
