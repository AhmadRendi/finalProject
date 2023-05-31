package org.example.repository;

import org.example.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface RepositoryProduct {


    List<Product> viewList() throws SQLException;

    List<Product> search(String cari);

    Integer stockProduct();

    void addProduct(Product product) throws SQLException;


    Product beli(Product product);

    void resetStock(int row, Integer sisa);

    void deleteProductById(int row);


}
