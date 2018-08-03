package com.example.cyui.demo.framework.page;

public class Page
{

	private long totalCount = 0;
	private int numPerPage = 10;
	private int pageNum = 1;
	private String orderBy;

	public Page()
	{
	}

	public Page(long totalCount, int numPerPage, int pageNum)
	{
		super();
		this.totalCount = totalCount;
		this.numPerPage = numPerPage;
		this.pageNum = pageNum;
	}

	public Page(long totalCount, int numPerPage, int pageNum, String orderBy)
	{
		super();
		this.totalCount = totalCount;
		this.numPerPage = numPerPage;
		this.pageNum = pageNum;
		this.orderBy = orderBy;
	}

	public long getTotalCount()
	{
		return totalCount;
	}

	public void setTotalCount(long totalCount)
	{
		this.totalCount = totalCount;
	}

	public int getNumPerPage()
	{
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage)
	{
		this.numPerPage = numPerPage;
	}

	public int getPageNum()
	{
		return pageNum;
	}

	public void setPageNum(int pageNum)
	{
		this.pageNum = pageNum;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
}
