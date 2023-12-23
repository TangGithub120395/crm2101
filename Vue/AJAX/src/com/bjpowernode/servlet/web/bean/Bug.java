package com.bjpowernode.servlet.web.bean;

import java.util.Objects;

public class Bug {
    private int id;
    private String detail;

    @Override
    public String toString() {
        return "Bug{" +
                "id=" + id +
                ", detail='" + detail + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bug bug = (Bug) o;
        return id == bug.id && Objects.equals(detail, bug.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, detail);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Bug() {
    }

    public Bug(int id, String detail) {
        this.id = id;
        this.detail = detail;
    }
}
