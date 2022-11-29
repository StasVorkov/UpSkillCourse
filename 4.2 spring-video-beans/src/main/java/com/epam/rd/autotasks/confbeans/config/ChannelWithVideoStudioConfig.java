package com.epam.rd.autotasks.confbeans.config;

import com.epam.rd.autotasks.confbeans.impl.VideoStudioCatAndCuriousImpl;
import com.epam.rd.autotasks.confbeans.video.Channel;
import com.epam.rd.autotasks.confbeans.video.Video;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Configuration
@PropertySource("classpath:FactoryCatAndCurious.properties")
public class ChannelWithVideoStudioConfig {
	
	@Value("${date}")
	String date;
	
	@Value("${nameVideo}")
	String nameVideo;
	
	@Value("${counter}")
	int counter;
	
	@Value("${episodes}")
	int amountOfEpisodes;
	
	@Value("${pattern}")
	String pattern;
	
	@Bean
	public VideoStudioCatAndCuriousImpl catAndCuriousImpl() {
		return new VideoStudioCatAndCuriousImpl(LocalDateTime.parse(date, DateTimeFormatter.ofPattern(pattern)),
				nameVideo, counter);
	}
	
	@Bean
	public Channel channel(VideoStudioCatAndCuriousImpl catAndCurious) {
		Channel channel = new Channel();
		for (int i = 0; i < amountOfEpisodes; i++) {
			channel.addVideo(catAndCurious.produce());
		}
		return channel;
	}
}
