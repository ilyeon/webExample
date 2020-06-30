package com.example.website.model;

public class Comment
{
    private int id;
    private int boardId;
    private String comment;
    private int userId;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBoardId() {
        return this.boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}