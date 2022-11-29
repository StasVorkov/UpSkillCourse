package com.epam.rd.autotasks.confbeans.impl;//package com.epam.rd.autotasks.confbeans.video;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.beans.factory.support.DefaultListableBeanFactory;
//import org.springframework.beans.factory.support.GenericBeanDefinition;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//
//public class VideoStudioPostProcessor implements BeanFactoryPostProcessor {
//
//
//	@Override
//	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//
//		Video video = beanFactory.getBean(Video.class);
//		System.out.println(video);
//		for (int i = 1; i < 9; i++) {
//			GenericBeanDefinition bd = new GenericBeanDefinition();
//			bd.setBeanClass(Video.class);
//			bd.getPropertyValues().add("name", "Cat and curious " + i);
//			bd.getPropertyValues().add("pubTime",
//					LocalDateTime.parse("2001-10-18 10:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
//
//			((DefaultListableBeanFactory) beanFactory)
//					.registerBeanDefinition("Video " + i, bd);
//		}
//	}
//}
//
//
