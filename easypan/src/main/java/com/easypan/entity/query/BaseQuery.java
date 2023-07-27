package com.easypan.entity.query;

/**
 * @Description 构建基础类 代码生成器生成
 */
public class BaseQuery {
    private SimplePage simplePage;
    private Integer pageNo;
    private Integer pageSize;
    private String orderBy;

    public SimplePage getSimplePage() {
        return simplePage;
    }

    public void setSimplePage(SimplePage simplePage) {
        this.simplePage = simplePage;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }


}
