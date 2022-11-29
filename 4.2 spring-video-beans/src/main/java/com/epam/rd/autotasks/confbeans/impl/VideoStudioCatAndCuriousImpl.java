package com.epam.rd.autotasks.confbeans.impl;

import com.epam.rd.autotasks.confbeans.video.Video;
import com.epam.rd.autotasks.confbeans.video.VideoStudio;

import java.time.LocalDateTime;

public class VideoStudioCatAndCuriousImpl implements VideoStudio {
	
	private LocalDateTime date;
	private String nameVideo;
	private int counter;
	
	public VideoStudioCatAndCuriousImpl(LocalDateTime date, String nameVideo, int counter) {
		this.date = date;
		this.nameVideo = nameVideo + " " + counter;
	}
	
	@Override
	public Video produce() {
		this.date = nextDate(this.date);
		this.nameVideo = nextName(this.nameVideo);
		return new Video(this.nameVideo, this.date);
	}
	
	private LocalDateTime nextDate(LocalDateTime date) {
		return date.plusYears(2);
	}
	
	private String nextName(String nameVideo) {
		return nameVideo.replace(Integer.valueOf(counter).toString(), Integer.valueOf(++counter).toString());
	}
}
