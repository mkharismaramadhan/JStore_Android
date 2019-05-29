package com.example.jstore_android_mkharismaramadhan;

public class Item {
    private  int id;
    private String name;
    private int price;
    private String category;
    private String Status;
    private Supplier supplier;

    public Item(int id, String name, int price, String category, String status, Supplier supplier) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        Status = status;
        this.supplier = supplier;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getStatus() {
        return Status;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}