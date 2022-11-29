package com.epam.rd.autotasks.confbeans.video;

import com.epam.rd.autotasks.confbeans.config.ChannelWithInjectedPrototypeVideoConfig;
import com.epam.rd.autotasks.confbeans.config.ChannelWithPhantomVideoStudioConfig;
import com.epam.rd.autotasks.confbeans.config.ChannelWithVideoStudioConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ChannelWithInjectedPrototypeVideoConfig.class);
		System.out.println("values from context " + (context.getBeansOfType(Video.class).values()));
		System.out.println("values from context " + (context.getBeansOfType(Channel.class).values()));
	}
}
