package com.easypan.entity.query;

import com.easypan.enums.PageSize;

import java.io.IOException;

/**
 *        pageNo: 当前页码， 数据库中的数据被拆分进一页一页（每页数据量自己规定
 *        countTotal: 总记录数
 *        pageSize: 页面可记录数据条数
 *        pageTotal: 页面数
 *        start: 记录起始位置
 *        end: 记录结束位置
 */
public class SimplePage {
    private Integer pageNo;
    private Integer countTotal;
    private Integer pageSize;
    private Integer pageTotal;
    private Integer start;
    private Integer end;

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setCountTotal(Integer countTotal) {
        this.countTotal = countTotal;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public SimplePage() {}

    public Integer getPageNo() {
        return pageNo;
    }

    public Integer getCountTotal() {
        return countTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public Integer getStart() {
        return start;
    }

    public Integer getEnd() {
        return end;
    }

    public SimplePage(Integer pageNo, Integer countTotal, Integer pageSize) throws IOException {
        if(null == pageNo) {
            pageNo = 0;
        }
        this.pageNo = pageNo;
        this.countTotal = countTotal;
        this.pageSize = pageSize;
        action();
    }

    public SimplePage(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }

    public void action() {
        if(this.pageSize <= 0) { //每页记录数<=0,
            this.pageSize = PageSize.SIZE20.getSize(); //设置默认数值20
        }
        if (this.countTotal > 0) { //总记录数 > 0
            this.pageTotal = this.countTotal % this.pageSize == 0 ? this.countTotal / this.pageSize : this.countTotal / this.pageSize + 1;
        } else {
            pageTotal = 1;
        }
        if (pageNo <= 1) { //当前页码
            pageNo = 1;
        }
        if (pageNo > pageTotal) {
            pageNo = pageTotal;
        }
        this.start = (pageNo - 1) * pageSize; //起始记录位置
        this.end = this.pageSize; //不是结束位置！！而是要查询的记录数
    }
}
