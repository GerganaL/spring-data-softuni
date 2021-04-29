package com.softuni.springintroex.services.models;

import java.math.BigDecimal;

public class BookInfo {
    private String title;
    private BigDecimal price;
    private int copies;

    public BookInfo(String title, BigDecimal price, int copies) {
        this.title = title;
        this.price = price;
        this.copies = copies;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getCopies() {
        return copies;
    }
}
