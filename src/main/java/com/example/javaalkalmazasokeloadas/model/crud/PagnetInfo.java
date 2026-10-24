package com.example.javaalkalmazasokeloadas.model.crud;

public class PagnetInfo<T> {
    private Integer currentPage;
    private Integer pageMaxCount;
    private Integer pageLength;
    private T data;

    public PagnetInfo() {}

    public PagnetInfo(Integer currentPage, Integer pageLength, Integer pageMaxCount, T data) {
        this.currentPage = currentPage;
        this.pageMaxCount = pageMaxCount;
        this.pageLength = pageLength;
        this.data = data;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageMaxCount() {
        return pageMaxCount;
    }

    public void setPageMaxCount(Integer pageMaxCount) {
        this.pageMaxCount = pageMaxCount;
    }

    public Integer getPageLength() {
        return pageLength;
    }

    public void setPageLength(Integer pageLength) {
        this.pageLength = pageLength;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
