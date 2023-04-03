package com.dipendra.blog.payload;

import java.util.List;

public class PostResponse {
    private List<PostDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totatPages;
    private boolean last;

    public PostResponse() {
    }

    public PostResponse(List<PostDto> content, int pageNo, int pageSize, long totalElements, int totatPages, boolean last) {
        this.content = content;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totatPages = totatPages;
        this.last = last;
    }

    public List<PostDto> getContent() {
        return content;
    }

    public void setContent(List<PostDto> content) {
        this.content = content;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotatPages() {
        return totatPages;
    }

    public void setTotatPages(int totatPages) {
        this.totatPages = totatPages;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostResponse that = (PostResponse) o;

        if (getPageNo() != that.getPageNo()) return false;
        if (getPageSize() != that.getPageSize()) return false;
        if (getTotalElements() != that.getTotalElements()) return false;
        if (getTotatPages() != that.getTotatPages()) return false;
        if (isLast() != that.isLast()) return false;
        return getContent().equals(that.getContent());
    }

    @Override
    public int hashCode() {
        int result = getContent().hashCode();
        result = 31 * result + getPageNo();
        result = 31 * result + getPageSize();
        result = 31 * result + (int) (getTotalElements() ^ (getTotalElements() >>> 32));
        result = 31 * result + getTotatPages();
        result = 31 * result + (isLast() ? 1 : 0);
        return result;
    }
}
