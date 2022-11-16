package com.encore.petandbe.utils.page;

import lombok.Getter;

@Getter
public class PageVO {
    private int endPage;
    private int startPage;
    private boolean nextPageExist;
    private boolean prevPageExist;
    private int realEndPage;
    private int selectPage;
    private int amountItem;
    private int totalItem;

    private Criteria cri;

    public PageVO(Criteria cri, int total) {
        this.cri = cri;
        this.amountItem = cri.getAmount();
        this.selectPage = cri.getPageNum();
        this.totalItem = total;

        this.endPage = (int) (Math.ceil(this.selectPage / 10.0)) * 10;
        this.startPage = this.endPage - 10 + 1;
        realEndPage = (int) (Math.ceil(this.totalItem / (double) this.amountItem));
        this.endPage = this.endPage > this.realEndPage ? this.realEndPage : this.endPage;
        this.prevPageExist = this.startPage > 1;
        this.nextPageExist = this.endPage < this.realEndPage;
    }
}
