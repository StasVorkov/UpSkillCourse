package com.rd.epam.autotasks.scopes.config.justasecond;

import com.rd.epam.autotasks.scopes.config.bean.JustSecondBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class JustSecondBeansConfig {

    @Scope(scopeName = "justASecond")
    @Bean("jBean1")
    public Object getJustSecondBean1() {
        return new JustSecondBean(1);
    }

    @Scope(scopeName = "justASecond")
    @Bean("jBean2")
    public Object getJustSecondBean2() {
        return new JustSecondBean(2);
    }
}
