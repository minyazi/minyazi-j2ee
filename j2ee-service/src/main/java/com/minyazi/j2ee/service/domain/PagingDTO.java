package com.minyazi.j2ee.service.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页DTO
 * 
 * @author minyazi
 */
public class PagingDTO<T> {
    /**
     * 总记录数
     */
    private int totalNumber;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 当前页码
     */
    private int currentPage;
    /**
     * 每页记录数
     */
    private int pageSize;
    /**
     * 当前页的首条记录索引
     */
    private int offset; 
    /**
     * 是否是第一页
     */
    private boolean firstPage;
    /**
     * 是否是最后一页
     */
    private boolean lastPage;
    /**
     * 是否有上一页
     */
    private boolean hasPreviousPage;
    /**
     * 是否有下一页
     */
    private boolean hasNextPage;
    /**
     * 当前页的所有记录
     */
    private List<T> data;
    
    /**
     * 构造一个分页DTO对象
     * 
     * @param page 页码
     * @param pageSize 每页记录数
     * @param totalNumber 总记录数
     */
    public PagingDTO(int page, int pageSize, int totalNumber) {
        // 设置总记录数
        setTotalNumber((totalNumber <= 0) ? 0 : totalNumber);
        // 设置每页记录数
        setPageSize((pageSize <= 0) ? 10 : pageSize);
        // 设置总页数
        setTotalPage((getTotalNumber() % getPageSize() == 0)
                ? (getTotalNumber() / getPageSize())
                : (getTotalNumber() / getPageSize() + 1));
        // 设置当前页码
        setCurrentPage((page <= 0) ? 1 : page);
        setCurrentPage((getCurrentPage() > getTotalPage())
                ? getTotalPage() : getCurrentPage());
        // 设置当前页的首条记录索引
        setOffset((getCurrentPage() == 0) ? 0 : (getPageSize() * (getCurrentPage() - 1)));
        // 判断是否是第一页
        setFirstPage(getCurrentPage() <= 1);
        // 判断是否是最后一页
        setLastPage(getCurrentPage() == getTotalPage());
        // 判断是否有上一页
        setHasPreviousPage(getTotalPage() > 1 && getCurrentPage() > 1);
        // 判断是否有下一页
        setHasNextPage(getTotalPage() > 1 && getCurrentPage() < getTotalPage());
        
        setData(new ArrayList<T>());
    }
    
    public int getTotalNumber() {
        return totalNumber;
    }
    
    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }
    
    public int getTotalPage() {
        return totalPage;
    }
    
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    
    public int getCurrentPage() {
        return currentPage;
    }
    
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    
    public int getPageSize() {
        return pageSize;
    }
    
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    public int getOffset() {
        return offset;
    }
    
    public void setOffset(int offset) {
        this.offset = offset;
    }
    
    public boolean isFirstPage() {
        return firstPage;
    }
    
    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }
    
    public boolean isLastPage() {
        return lastPage;
    }
    
    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }
    
    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }
    
    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }
    
    public boolean isHasNextPage() {
        return hasNextPage;
    }
    
    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }
    
    public List<T> getData() {
        return data;
    }
    
    public void setData(List<T> data) {
        this.data = data;
    }
    
    @Override
    public String toString() {
        if (data == null) {
            return super.toString();
        }
        return data.toString();
    }
}
