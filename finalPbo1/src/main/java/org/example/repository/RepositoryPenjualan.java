package org.example.repository;

import org.example.entity.Penjualan;

import java.util.List;

public interface RepositoryPenjualan {

    List<Penjualan> list();

    void insert(Penjualan penjualan);


}
