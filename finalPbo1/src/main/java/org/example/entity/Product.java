package org.example.entity;

public class Product {


    private Integer id;
    private Integer harga;
    private String name;

    private Integer stock;

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Product() {
    }

    public Product(Integer harga, String name, Integer stock) {
        this.harga = harga;
        this.name = name;
        this.stock = stock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public String getNameBarang() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
