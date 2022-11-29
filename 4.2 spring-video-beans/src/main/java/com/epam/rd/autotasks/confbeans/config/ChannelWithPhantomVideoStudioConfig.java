package com.epam.rd.autotasks.confbeans.config;

import com.epam.rd.autotasks.confbeans.video.Channel;
import com.epam.rd.autotasks.confbeans.video.Video;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;

@Configuration
public class ChannelWithPhantomVideoStudioConfig {
	
	private static final String NAME_VIDEO = "Cat & Curious ";
	private static final int AMOUNT_OF_EPISODES = 8;
	private static final int YEARS_STEP = 2;
	private static int counter = 1;
	private static LocalDateTime dateTime = LocalDateTime.of(2001, 10, 18, 10, 0);
	
	@Bean
	@Scope(value = "prototype")
	public Video catVideo() {
		return getVideo();
	}
	
	private Video getVideo() {
		Video video = new Video(NAME_VIDEO + counter, dateTime);
		updateVideoData();
		return video;
	}
	
	private void updateVideoData() {
		counter++;
		dateTime = dateTime.plusYears(YEARS_STEP);
	}
	
	@Bean
	public Channel channel() {
		Channel channel = new Channel();
		while (counter <= AMOUNT_OF_EPISODES) {
			channel.addVideo(catVideo());
		}
		return channel;
	}
}
