package com.epam.rd.autotasks.confbeans.config;

import com.epam.rd.autotasks.confbeans.impl.VideoStudioBoilWaterImpl;
import com.epam.rd.autotasks.confbeans.impl.VideoStudioBuildHouseImpl;
import com.epam.rd.autotasks.confbeans.impl.VideoStudioEscapeSolitudeImpl;
import com.epam.rd.autotasks.confbeans.video.Channel;
import com.epam.rd.autotasks.confbeans.video.Video;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SingletonChannelConfig {
	
	@Bean("video1")
	public Video videoStudioBoilWaterImpl() {
		return new VideoStudioBoilWaterImpl().produce();
	}
	
	@Bean("video2")
	public Video videoStudioBuildHouseImpl() {
		return new VideoStudioBuildHouseImpl().produce();
	}
	
	@Bean("video3")
	public Video videoStudioEscapeSolitudeImpl() {
		return new VideoStudioEscapeSolitudeImpl().produce();
	}
	
	@Bean
	public Channel channel() {
		Channel channel = new Channel();
		channel.addVideo(videoStudioBoilWaterImpl());
		channel.addVideo(videoStudioBuildHouseImpl());
		channel.addVideo(videoStudioEscapeSolitudeImpl());
		return channel;
	}
	
	
}
