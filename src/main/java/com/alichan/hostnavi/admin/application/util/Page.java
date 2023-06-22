package com.alichan.hostnavi.admin.application.util;

import java.util.List;
import com.github.pagehelper.PageInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Page<T> {
  private Integer pageNumber;
  private Integer pageSize;
  private Integer totalPage;
  private Long total;
  private List<T> list;

  public Page(List<T> list) {
    PageInfo<T> pageInfo = new PageInfo<T>(list);
    this.pageNumber = pageInfo.getPageNum();
    this.pageSize = pageInfo.getPageSize();
    this.totalPage = pageInfo.getPages();
    this.total = pageInfo.getTotal();
    this.list = list;
  }
}
