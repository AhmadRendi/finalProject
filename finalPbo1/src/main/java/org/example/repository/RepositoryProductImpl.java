package org.example.repository;

import org.example.config.DatabaseConfig;
import org.example.entity.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositoryProductImpl implements RepositoryProduct{

    private DataSource dataSource = DatabaseConfig.getConnetion();


    @Override
    public List<Product> viewList() throws SQLException {

        String sql = """
                select * from product;
                """;

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()){

            List<Product> list = new ArrayList<>();

            while(resultSet.next()){
                Product product = new Product();
                product.setName(resultSet.getString("nama_product"));
                product.setHarga(resultSet.getInt("harga"));
                product.setStock(resultSet.getInt("stock"));
                list.add(product);
            }
            return list;


        }catch (SQLException exception){
            throw new SQLException("gagal");
        }
    }


    @Override
    public List<Product> search(String cari) {
        String sql = """
                select * from product 
                where nama_product like ?;
                """;
        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,"%" + cari + "%");
            List<Product> products = new ArrayList<>();
            try(ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()){
                    Product product = new Product();
                    product.setName(resultSet.getString("nama_product"));
                    product.setHarga(resultSet.getInt("harga"));
                    product.setStock(resultSet.getInt("stock"));
                    product.setId(resultSet.getInt("id"));
                    products.add(product);
                }
                return products;
            }
        }catch (SQLException e){
            throw new RuntimeException("gagal terhubung");
        }
    }

    @Override
    public Integer stockProduct() {

        return null;
    }

    @Override
    public void addProduct(Product product) throws SQLException {

        String sql = """
                insert into product(nama_product, harga, stock) values 
                (?,?,?);
                """;

        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, product.getNameBarang());
            statement.setInt(2, product.getHarga());
            statement.setInt(3, product.getStock());

            statement.executeUpdate();

        }catch (SQLException e){
            throw new SQLException("nfoew");
        }
    }

    @Override
    public void resetStock(int row, Integer sisa) {
        String sql = """
                update product 
                set stock = ?
                where id = ?;
                """;

        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, sisa);
            statement.setInt(2, row);
            statement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("gagal terhubung");
        }
    }

    @Override
    public Product beli(Product product) {
        return null;
    }


    @Override
    public void deleteProductById(int row) {
        String sql = """
                delete from product 
                where id = ?;
                """;

        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, row);
            statement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("terjadi kesalahan");
        }
    }
}
