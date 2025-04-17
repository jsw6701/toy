package com.example.toy.common.paging;

import lombok.Data;
import org.springframework.data.domain.PageRequest;

@Data
public abstract class Paging {

  public static final String CURR_PAGE_NO = "currPageNo";
  public static final String FIRST_PAGE_NO = "firstPageNo";
  public static final String LAST_PAGE_NO = "lastPageNo";

  private int pageNo;
  private int pageRow;
  private int totalCount;

  public int getTotalPageCount() {
    if (totalCount <= 0 || pageRow <= 0) {
      return 1;
    }
    return Math.max((int) Math.ceil((double) totalCount / (double) pageRow), 1);
  }

  public int getOffsetNo() {
    return (getCurrentPageNo() - 1) * pageRow;
  }

  public int getCurrentPageNo() {
    return Math.min(pageNo, getTotalPageCount());
  }

  public int getFirstPageNo() {
    return 1;
  }

  public int getLastPageNo() {
    return getTotalPageCount();
  }

  public PageRequest toPageRequest() {
    return PageRequest.of(Math.max(pageNo - 1, 0), pageRow);
  }

  public boolean isPageable() {
    return pageNo > 0 && pageRow > 0;
  }
}
