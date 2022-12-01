package com.rd.epam.autotasks.scopes.config.thread;

import com.rd.epam.autotasks.scopes.config.bean.ThreadBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ThreadBeansConfig {

    @Scope(scopeName = "thread")
    @Bean("threadBean1")
    public Object getThreeTimesBean1() {
        return new ThreadBean("first");
    }

    @Scope(scopeName = "thread")
    @Bean("threadBean2")
    public Object getThreeTimesBean2() {
        return new ThreadBean("second");
    }

}
