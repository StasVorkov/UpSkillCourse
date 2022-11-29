package com.epam.rd.autotasks.confbeans.config;

import com.epam.rd.autotasks.confbeans.video.Channel;
import com.epam.rd.autotasks.confbeans.video.Video;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;
import java.util.stream.Stream;

@Configuration
@PropertySource("classpath:FailureCat.properties")
public class ChannelWithInjectedPrototypeVideoConfig extends Channel {
	
	private static LocalDateTime dateTime = LocalDateTime.of(2001, 10, 18, 10, 0);
	
	@Value("${nameVideo}")
	String nameVideo;
	
	@Value("${daysStep}")
	int daysStep;
	
	@Value("${lastDay}")
	int lastDay;
	
	
	@Bean("channel")
	public Channel getBeanChanel() {
		return new ChannelWithInjectedPrototypeVideoConfig();
	}
	
	@Bean
	@Scope("prototype")
	public Video getBeanVideo() {
		return getVideo();
	}
	
	@Override
	public Stream<Video> videos() {
		Channel localChannel = new Channel();
		int currentDay = daysStep;
		while (currentDay <= lastDay) {
			localChannel.addVideo(getVideo());
			currentDay++;
		}
		return localChannel.videos();
	}
	
	private Video getVideo() {
		Video video = new Video(nameVideo, dateTime);
		updateRelease();
		return video;
	}
	
	private void updateRelease() {
		dateTime = dateTime.plusDays(daysStep);
	}
}
