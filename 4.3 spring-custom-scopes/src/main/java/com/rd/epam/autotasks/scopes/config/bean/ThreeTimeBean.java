package com.rd.epam.autotasks.scopes.config.bean;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ThreeTimeBean {

    private final long id;
    private final String name;

    public ThreeTimeBean(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThreeTimeBean employee = (ThreeTimeBean) o;
        return id == employee.id &&
                Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Employee[" + "id=" + id + ", name=" + name + "]";
    }
}
