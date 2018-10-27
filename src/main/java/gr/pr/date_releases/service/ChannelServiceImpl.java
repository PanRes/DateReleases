package gr.pr.date_releases.service;

import gr.pr.date_releases.dao.ChannelDao;
import gr.pr.date_releases.dao.GenericDao;
import gr.pr.date_releases.entity.SeriesTVChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelServiceImpl implements ChannelService {
	
	@Autowired
	private ChannelDao channelDao;

	@Autowired
	private GenericDao genericDao;
	
	public List<SeriesTVChannel> getAllChannels() {
		return channelDao.getAllChannels();
	}

	@Override
	public SeriesTVChannel createChannel(String channel) {
		try {
			SeriesTVChannel tvChannel = new SeriesTVChannel(channel);
			genericDao.save(tvChannel);
		}
		catch (Exception e) {
			System.out.println("Channel already exists");
		}

		return channelDao.getChannelByName(channel);

	}

	@Override
	public SeriesTVChannel getChannelById(int channelId) {
		return channelDao.getChannelById(channelId);
	}
}
