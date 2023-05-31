package org.example.repository;

import org.example.config.DatabaseConfig;
import org.example.entity.Penjualan;

import javax.sql.DataSource;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositoryPenjualanImpl implements RepositoryPenjualan{

    DataSource dataSource = DatabaseConfig.getConnetion();

    @Override
    public List<Penjualan> list() {

        String sql = """
                select * from penjualan;
                """;

        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()){

            List<Penjualan> penjualanList = new ArrayList<>();
            while (resultSet.next()){
                Penjualan penjualan = new Penjualan();
                penjualan.setName(resultSet.getString("nama_barang"));
                penjualan.setHarga(resultSet.getInt("harga"));
                penjualan.setJumlah(resultSet.getInt("jumlah"));
                penjualan.setTotal(resultSet.getInt("total"));
                penjualan.setId(resultSet.getInt("id"));
                penjualanList.add(penjualan);
            }
            return penjualanList;
        }catch (SQLException e){
            throw new RuntimeException("kososng");
        }
    }

    @Override
    public void insert(Penjualan penjualan) {
        String sql = """
                insert into penjualan(nama_barang, harga,jumlah, total) values 
                (?, ?,?,?)
                """;

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            String name = penjualan.getName();
            Integer harga = penjualan.getHarga();
            Integer jumlah = penjualan.getJumlah();
            Integer total = penjualan.getTotal();

            statement.setString(1, name);
            statement.setInt(2,harga);
            statement.setInt(3, jumlah);
            statement.setInt(4, total);
            JOptionPane.showMessageDialog(null, "berhasil");
            statement.executeUpdate();

        }catch (SQLException e){
            throw  new RuntimeException(e.getMessage());
        }
    }

    public void deleteByRow(int row){
        String sql = """
                delete from penjualan 
                where id = ?;
                """;

        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, row);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "sampai disini");
        }catch (SQLException e){
            throw new RuntimeException("gagal menghapus");
        }
    }

}
