package org.example.service;

import org.example.entity.Penjualan;
import org.example.entity.Product;
import org.example.model.ModelPenjualan;
import org.example.model.ModelProduct;
import org.example.repository.RepositoryPenjualanImpl;
import org.example.repository.RepositoryProductImpl;
import org.example.view.HomeAnggota;
import org.example.view.ViewPenjualan;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class ServicePenjualanImpl {

    private ViewPenjualan penjualan;

    private RepositoryPenjualanImpl repositoryPenjualan;

    private  ServiceProductImpl serviceProduct = new ServiceProductImpl();

    private RepositoryProductImpl repositoryProduct = new RepositoryProductImpl();
    private List<Penjualan> list;

    private List<Product> listProduct;

    public ServicePenjualanImpl() {
    }

    public ServicePenjualanImpl(ViewPenjualan viewPenjualan) throws SQLException {
        this.penjualan = viewPenjualan;
        this.repositoryPenjualan = new RepositoryPenjualanImpl();
        this.list = repositoryPenjualan.list();
    }

    public void tblBarang() throws SQLException {
        list = repositoryPenjualan.list();

        ModelPenjualan modelBarang = new ModelPenjualan(list);

        penjualan.getTblListBarang().setModel(modelBarang);
    }

    public void totalBayar(){
        list = repositoryPenjualan.list();
        Integer total = 0;
        for (int i = 0; i < list.size(); i++){
            total += list.get(i).getTotal();
        }
        penjualan.getTampilTotal().setText(total.toString());
    }

    public int selectIdByNameProduct(int row) throws SQLException {
        listProduct = repositoryProduct.viewList();
        String names = list.get(row).getName();
        listProduct = repositoryProduct.search(names);
        int id = 0;
        for (var value : listProduct){
            id = value.getId();
        }
        return id;
    }

    public int selectIdByNamePenjualan(int row) throws SQLException {
        listProduct = repositoryProduct.viewList();
        String names = list.get(row).getName();
        listProduct = repositoryProduct.search(names);
        int id = 0;
        for (int i = 0; i < list.size(); i++){
            if(names.equals(list.get(i).getName())){
                id = list.get(i).getId();
            }
        }
        return id;
    }


    public void pilih(int row) throws SQLException {
        list = repositoryPenjualan.list();
        String names = list.get(row).getName();
        listProduct = repositoryProduct.viewList();

        for (int i = 0; i < listProduct.size(); i++){
            if(names.equals(listProduct.get(i).getNameBarang())){
                int kembaliStock = list.get(row).getJumlah();
                kembaliStock += listProduct.get(i).getStock();
                int id = selectIdByNameProduct(row);
                repositoryProduct.resetStock(id, kembaliStock);
            }
        }
    }

    public void ubah(int row) throws SQLException {
        pilih(row);
        int id = selectIdByNamePenjualan(row);
        repositoryPenjualan.deleteByRow(id);
        tblBarang();
        totalBayar();
    }

}
