package com.example.website.model;

public class Criteria {
    private int currentPage = 1; // 현재 페이지 번호(아래메뉴)
    private int perPageNum = 10; // 페이지당 보여줄 글 개수 (리미트끝)
    private int startPageNum = 0; // 리미트시작점

    private String titleSearch;

    public int getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        if(currentPage <=0)
        {
            this.currentPage = 1;
        }
    }

    public int getPerPageNum() {
        return this.perPageNum;
    }

    public void setPerPageNum(int perPageNum) {
        this.perPageNum = perPageNum;
    }

    public int getStartPageNum() {
        return (this.currentPage -1)*perPageNum;
    }
    
    public String getTitleSearch() {
        return this.titleSearch;
    }

    public void setTitleSearch(String titleSearch) {
        this.titleSearch = titleSearch;
    }
}