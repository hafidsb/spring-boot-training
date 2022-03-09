package com.example.demo.dto;

public class UpdateStockDto {
    private long id;
    private long stockUpdate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStockUpdate() {
        return stockUpdate;
    }

    public void setStockUpdate(long stockUpdate) {
        this.stockUpdate = stockUpdate;
    }
}
