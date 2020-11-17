package com.cb.signupstage.dto;

import com.github.pagehelper.Page;

import java.util.List;

/**
 * @author: ly
 * @time: 2020/11/12 13:41
 * @description:
 */
public class PagedResult<T>
{
    private static final long serialVersionUID = 1L;

    private List<T> dataList;// 数据

    private long pageNum;// 当前页

    private long pageSize;// 条数

    private long total;// 总条数

    private long pages;// 总页面数目

    public PagedResult()
    {
    }

    public PagedResult(List<T> dataList)
    {
        if (dataList instanceof Page)
        {
            Page<T> page = (Page<T>) dataList;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.total = page.getTotal();
            this.pages = page.getPages();
            this.dataList = page;
        }
    }

    public List<T> getDataList()
    {
        return dataList;
    }

    public void setDataList(List<T> dataList)
    {
        this.dataList = dataList;
    }

    public long getPageNum()
    {
        return pageNum;
    }

    public void setPageNum(long pageNum)
    {
        this.pageNum = pageNum;
    }

    public long getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(long pageSize)
    {
        this.pageSize = pageSize;
    }

    public long getTotal()
    {
        return total;
    }

    public void setTotal(long total)
    {
        this.total = total;
    }

    public long getPages()
    {
        return pages;
    }

    public void setPages(long pages)
    {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "PagedResult{" +
                "dataList=" + dataList +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", pages=" + pages +
                '}';
    }
}
