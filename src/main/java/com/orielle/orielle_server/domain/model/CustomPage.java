package com.orielle.orielle_server.domain.model;

import java.util.List;

public class CustomPage<T> {
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private List<T> content;

    public CustomPage() {
    }

    public CustomPage(int pageNumber, int pageSize, long totalElements, int totalPages, List<T> content) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.content = content;
    }

    public int getPageNumber() { return pageNumber; }

    public void setPageNumber(int pageNumber) { this.pageNumber = pageNumber; }

    public int getPageSize() { return pageSize; }

    public void setPageSize(int pageSize) { this.pageSize = pageSize; }

    public long getTotalElements() { return totalElements; }

    public void setTotalElements(long totalElements) { this.totalElements = totalElements; }

    public int getTotalPages() { return totalPages; }

    public void setTotalPages(int totalPages) { this.totalPages = totalPages; }

    public List<T> getContent() { return content; }

    public void setContent(List<T> content) { this.content = content; }
}
