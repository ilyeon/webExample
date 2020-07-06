package com.example.website.model;

import java.util.ArrayList;
import java.util.List;

public class pageMaker
{
    private Criteria criteria; // 현재페이지 페이지당게시글수 시작인덱스
    private int totalCount; // 데이터 전체 개수
    private int startPage; // 페이지번호 시작번호 (5개씩 보여주면 1 6 11 ...)
    private int endPage; // 페이지번호 마지막번호 (ex 5개씩 보여주면 5 10 ....)
    private boolean prev; // 이전버튼 생성여부(th:if용)
    private boolean next; // 다음버튼 생성여부 ^
    private int displayPageNum = 5; // 페이지버튼 개수
    private List<Integer> pageButton = new ArrayList<Integer>();

    //페이지버튼 생성 (prev next 값 결정)
    private void calcData()
    {
        // 보여줄 페이지번호 마지막 < 현재 페이지 번호랑 보여줄 개수를 나눠서 올림처리하고 걔를 다시 보여줄 개수로 곱셈
        endPage = (int) (Math.ceil(criteria.getCurrentPage() / (double) displayPageNum) * displayPageNum);

        // 보여줄 페이지번호 맨앞
        startPage = (endPage - displayPageNum) +1;
        if(startPage <= 0) startPage = 1;

        int tempEndPage = (int) (Math.ceil(totalCount / (double) criteria.getPerPageNum()));
        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }
        
        //이전다음버튼 활성화 
        prev = startPage == 1 ? false : true;
        next = endPage * criteria.getPerPageNum() < totalCount ? true : false;

        for(int i=startPage; i<endPage+1; i++)
        {
            pageButton.add(i);
        }
    }

    public Criteria getCriteria() {
        return this.criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        calcData();
    }

    public int getStartPage() {
        return this.startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return this.endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public boolean isPrev() {
        return this.prev;
    }

    public void setPrev(boolean prev) {
        this.prev = prev;
    }

    public boolean isNext() {
        return this.next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public int getDisplayPageNum() {
        return this.displayPageNum;
    }

    public void setDisplayPageNum(int displayPageNum) {
        this.displayPageNum = displayPageNum;
    }

    public List<Integer> getpageButton() {
        return this.pageButton;
    }

    public void setpageButton(List<Integer> pageButton) {
        this.pageButton = pageButton;
    };
}