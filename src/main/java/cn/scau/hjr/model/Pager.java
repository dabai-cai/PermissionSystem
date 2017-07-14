package cn.scau.hjr.model;

import java.util.List;

/**
 * Created by Administrator on 2017/7/14 0014.
 */
public class Pager {

    private int totalGuest;//总共有多少条留言
    private int totalPage;//总共有多少页
    private int pageSize;//每页有多少条留言
    private int pageOffset;//当前是第几页
    private List pagerData;//保存了当前页面的留言
    private int start;

    public List getPagerData() {
        return pagerData;
    }

    public void setPagerData(List pagerData) {
        this.pagerData = pagerData;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotalGuest() {
        return totalGuest;
    }

    public Pager(int totalGuest, int totalPage, int pageSize, int _pageOffset, List pagerData) {
        this.totalGuest = totalGuest;
        this.totalPage = totalPage;
        this.pageSize = pageSize;
        this.pageOffset = _pageOffset;
        this.pagerData = pagerData;
    }

    public Pager() {

    }

    public void setTotalGuest(int totalGuest) {
        this.totalGuest = totalGuest;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageOffset() {
        return pageOffset;
    }

    public void setPageOffset(int _pageOffset) {
        this.pageOffset = _pageOffset;
    }

    public List getpagerData() {
        return pagerData;
    }

    public void setpagerData(List pagerData) {
        this.pagerData = pagerData;
    }
}
