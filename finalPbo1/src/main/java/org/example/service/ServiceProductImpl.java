package org.example.service;

import org.example.entity.Penjualan;
import org.example.entity.Product;
import org.example.model.ModelProduct;
import org.example.repository.RepositoryPenjualan;
import org.example.repository.RepositoryPenjualanImpl;
import org.example.repository.RepositoryProduct;
import org.example.repository.RepositoryProductImpl;
import org.example.view.HomeAnggota;
import org.example.view.ViewLoginPegawai;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class ServiceProductImpl {

    private HomeAnggota homeAnggota;

    private RepositoryProduct repositoryProduct;

    private ViewLoginPegawai loginPegawai;

    private List<Product> list;

    public ServiceProductImpl() {
    }

    public ServiceProductImpl(HomeAnggota homeAnggota) throws SQLException {
        this.homeAnggota = homeAnggota;
        this.repositoryProduct = new RepositoryProductImpl();
        this.list = repositoryProduct.viewList();
    }

    /**
     *
     * @param viewLoginPegawai ini digunakan pada pemanggilan di class ViewLoginPegawai dan akan
     *                         digunakan diclass tersebut
     * @throws SQLException ini akan mengembalikan nilai exception apabila terjadi kesalahan saat
     * proses pemanggilan, pemprosesan, penginputan yang berhubungan dengan database
     */
    public ServiceProductImpl(ViewLoginPegawai viewLoginPegawai) throws SQLException {
        this.loginPegawai = viewLoginPegawai;
        this.repositoryProduct = new RepositoryProductImpl();
        this.list = repositoryProduct.viewList();
    }

    /**
     * ini digunakan untuk menampilkan list bran yang akan dijual
     * @throws SQLException
     */
    public void tblBarang() throws SQLException {
        list = repositoryProduct.viewList();

        ModelProduct modelBarang = new ModelProduct(list);

        homeAnggota.getTblListBarang().setModel(modelBarang);
    }

    /**
     * ini untuk menampilkan barang yang baru saja diinputkan oleh pegawai atau karyawan didalam
     * tabel
     * @throws SQLException
     */
    public void tblBarangKaryawan() throws SQLException {
        list = repositoryProduct.viewList();

        ModelProduct modelBarang = new ModelProduct(list);

        loginPegawai.getjTable1().setModel(modelBarang);
    }

    /**
     * digunakan untuk mereset kembali nilai atau value yang telah diinptkan apabila batal untuk
     * menginputkannya
     */
    public void reset(){
        loginPegawai.getTextNamaBarang().setText("");
        loginPegawai.getTextHarga().setText("");
        loginPegawai.getTextStock().setText("");
    }

    /**
     * ini di gunakan untuk mereset kembali nilai atau value yang kita masukan
     * sebagai jumlah barang yang ingin dibeli mejadi nol kembali
     */
    public void resetBeli(){
        homeAnggota.getTextJumlah().setText("");
    }

    /**
     * digunakan untuk menyimpan nilai atau value dari barang yang ingin dijual
     * dimana ini diinputkan oleh karyawann
     * @throws SQLException
     */
    public void save() throws SQLException {
        Product product = new Product();

        product.setName(loginPegawai.getTextNamaBarang().getText());
        product.setHarga(Integer.valueOf(loginPegawai.getTextHarga().getText()));
        product.setStock(Integer.valueOf(loginPegawai.getTextStock().getText()));

        repositoryProduct.addProduct(product);

    }

    /**
     *
     * @param row digunakan untuk menunjukan pada index ke berapa didalam array diambil
     *            yang berdasarkan click dari mouse yang didialam tabel
     * @return hasil dari pengurangan dari stok product dengan yang dibeli oleh pembeli
     */
    public Integer hasilKurang(int row){
        String jumlahBarangBeli = homeAnggota.getTextJumlah().getText();
        Integer jumlahBarang = Integer.valueOf(jumlahBarangBeli);
        Integer stockBarangSaatIni = list.get(row).getStock();
        Integer hasil = 0;
        if(stockBarangSaatIni >= jumlahBarang){
            hasil = stockBarangSaatIni - jumlahBarang;
        }
        return  hasil;
    }

    /**
     *
     * @param row untuk mengambil colum keberapa yang di pilih oleh user
     * @return ini mengembalikan nilai true atau false dimana jika
     * pesanan kita lebih besar dari stock barang saat ini maka akan mengembalikan nilai false
     * sedangkan apabila jmlah yang ingin dibeli lebih besar dari julah stock saat ini maka
     * akan mengembalikan nilai true
     */
    private boolean cekStockBeli(int row){
        Integer jmlBrgBeli = Integer.valueOf(homeAnggota.getTextJumlah().getText());
        Integer stockBrgSaatIni = list.get(row).getStock();
        if(stockBrgSaatIni >= jmlBrgBeli){
            return true;
        }else {
            return false;
        }
    }

    /**
     *
     * @param row untuk mengambil colum keberapa yang di pilih oleh user
     * @param sisa ini akan diisi oleh hasil dari fungtion hasilKurang(int row)
     *             yang telah kita buat diatas
     */
    public void sisaStock(int row, Integer sisa) throws SQLException {
        int potition = selectIdByName(row);
        repositoryProduct.resetStock(potition , sisa);
    }

    /**
     *
     * @param row untuk mengambil colum keberapa yang di pilih oleh user
     *
     *            ini akan menammpilkan nilai dari colum yang dipilih
     */
    public void jual(int row){
        homeAnggota.getTextNamaBarang().setText(list.get(row).getNameBarang());
        homeAnggota.getTextHargaBarang().setText(String.valueOf(list.get(row).getHarga()));
    }

    /**
     * ini digunakan apabila kita telah memilih untuk membeli sesuatu barang maka
     * barang yang telah dipilih tadi akan pindah menjadi barang yang dibeli
     */
    public void beli() throws SQLException {
        Penjualan penjualan = new Penjualan();
        penjualan.setName(homeAnggota.getTextNamaBarang().getText());
        penjualan.setHarga(Integer.valueOf(homeAnggota.getTextHargaBarang().getText()));
        penjualan.setJumlah(Integer.valueOf(homeAnggota.getTextJumlah().getText()));
        Integer harga = penjualan.getHarga();
        Integer jumlah = penjualan.getJumlah();
        Integer total = harga * jumlah;

        penjualan.setTotal(total);
        penjualan.setJumlah(jumlah);

        RepositoryPenjualan repositoryPenjualan = new RepositoryPenjualanImpl();
        if(cekStockBeli(homeAnggota.getTblListBarang().getSelectedRow())){
            repositoryPenjualan.insert(penjualan);
            Integer sisaBarang = hasilKurang(homeAnggota.getTblListBarang().getSelectedRow());
            sisaStock(homeAnggota.getTblListBarang().getSelectedRow(), sisaBarang);
            JOptionPane.showMessageDialog(null, "sisa brang " + sisaBarang);
            resetBeli();
            tblBarang();
        }else {
            JOptionPane.showMessageDialog(null, "stock kurang atau habis");
        }
    }


    /**
     * ini digunakan untuk menampilkan secara langsung barang yang dicari didalam tabel
     * list barang yang dijual
     */
    private void isiTable(){
        if(!homeAnggota.getSearchBarang().getText().isBlank()
        || !homeAnggota.getSearchBarang().getText().isBlank()){
            list = repositoryProduct.search(homeAnggota.getSearchBarang().getText());
            ModelProduct modelProduct = new ModelProduct(list);
            homeAnggota.getTblListBarang().setModel(modelProduct);
        }
    }


    /**
     * ini digunakan untuk mencari nama barang yang ingin dicari
     */
    public void cariNama(){
        if(!homeAnggota.getSearchBarang().getText().isBlank()
                || !homeAnggota.getSearchBarang().getText().isBlank()){
            repositoryProduct.search(homeAnggota.getSearchBarang().getText());
            isiTable();
        }else {
            JOptionPane.showMessageDialog(null, "Silahkan isi");
        }
    }

    public void deleteById(int row) throws SQLException {
        repositoryProduct.deleteProductById(row);
        tblBarangKaryawan();
    }

    public int findIdProduct(int row){
        int id;
        id = loginPegawai.getjTable1().getSelectedRow();
        return id;
    }

    public int selectIdByName(int row) throws SQLException {
        list = repositoryProduct.viewList();
        String names = list.get(row).getNameBarang();
        list = repositoryProduct.search(names);
        int id = 0;
        for (var value : list){
            id = value.getId();
        }
        return id;
    }

    public void updateKaryawan(int row) throws SQLException {
        list = repositoryProduct.viewList();
        loginPegawai.getTextNamaBarang().setText(list.get(row).getNameBarang());
        loginPegawai.getTextHarga().setText(String.valueOf(list.get(row).getHarga()));
    }

    public void updateStock(int row, int id){
        String stcok = loginPegawai.getTextStock().getText();
        int stockTambah = Integer.valueOf(stcok);
        Integer stockBrngSaatIni = list.get(row).getStock();
        stockTambah += stockBrngSaatIni;
        repositoryProduct.resetStock(id, stockTambah);
        reset();
    }

}
