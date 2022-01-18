package com.myAppium.script.c3IOT.floorControlMannagement.spaceMannagement.api.entity;

public class ListParkRequest {
    public String keyword;
    public Integer pageNum;
    public Integer pageSize;
    public Integer state;

    @Override
    public String toString() {
        return "ListPark{" +
                "keyword='" + keyword + '\'' +
                ", pageNum='" + pageNum + '\'' +
                ", pageSize='" + pageSize + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public String getKeyword() {
        return keyword;
    }

    public ListParkRequest setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public ListParkRequest setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public ListParkRequest setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Integer getState() {
        return state;
    }

    public ListParkRequest setState(Integer state) {
        this.state = state;
        return this;
    }
}
