package com.example.website.model;

import java.sql.Timestamp;

public class Board
{
	private int id;
	private String title;
	private String content;
	private int viewCount;
	private int userId;
	private Timestamp dateTime;
	private String writer;

	public int getId()
	{
		return this.id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return this.title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getContent()
	{
		return this.content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public int getViewCount()
	{
		return this.viewCount;
	}

	public void setViewCount(int viewCount)
	{
		this.viewCount = viewCount;
	}

	public int getuserId() {
		return this.userId;
	}

	public void setuserId(int userId) {
		this.userId = userId;
	}

	public Timestamp getDateTime()
	{
		return this.dateTime;
	}

	public void setDateTime(Timestamp dateTime)
	{
		this.dateTime = dateTime;
	}

	public String getWriter() {
		return this.writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}
}