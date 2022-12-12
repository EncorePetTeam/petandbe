package com.encore.petandbe.utils.page;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Criteria {

    private int pageNum;
    private int amount;

    public Criteria() {
        this.pageNum = 1;
        this.amount = 10;
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }

    public int getPageStart() {
        return (pageNum - 1) * amount;
    }
}
