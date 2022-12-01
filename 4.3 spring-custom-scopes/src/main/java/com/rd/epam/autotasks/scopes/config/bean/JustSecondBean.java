package com.rd.epam.autotasks.scopes.config.bean;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Objects;

@Component
public class JustSecondBean {

    private final int id;
    
    public JustSecondBean(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JustSecondBean that = (JustSecondBean) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{obj " + id + '}';
    }
}
