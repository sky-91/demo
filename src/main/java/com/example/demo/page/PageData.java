package com.example.demo.page;

import java.util.List;

public class PageData<O> {

  /**
   * 查询内容
   */
  private List<O> content;
  /**
   * 页码
   */
  private int pageNum;
  /**
   * 页面大小
   */
  private int pageSize;
  /**
   * 总页数
   */
  private int pages;
  /**
   * 总数
   */
  private long total;

  public PageData() {
  }

  public PageData(int pageNum, int pageSize, int pages, long total, List<O> content) {
    this.pageNum = pageNum;
    this.pageSize = pageSize;
    this.pages = pages;
    this.total = total;
    this.content = content;
  }

  public int getPageNum() {
    return pageNum;
  }

  public void setPageNum(int pageNum) {
    this.pageNum = pageNum;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getPages() {
    return pages;
  }

  public void setPages(int pages) {
    this.pages = pages;
  }

  public long getTotal() {
    return total;
  }

  public void setTotal(long total) {
    this.total = total;
  }

  public List<O> getContent() {
    return content;
  }

  public void setContent(List<O> content) {
    this.content = content;
  }

}
