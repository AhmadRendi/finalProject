package org.example.repository;

import org.example.config.DatabaseConfig;
import org.example.entity.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RepositoryUserImpl implements RepositoryUsers{
    DataSource dataSource= DatabaseConfig.getConnetion();
    @Override
    public List<User> viewList() {
        return null;
    }


    @Override
    public void daftar(User user) throws SQLException {
        String sql = """
                Insert into user(nama, username, password, alamat, noHp, jenis_kelamin, saldo) 
                values (?,?,?,?,?,?,?);
                """;



        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setObject(1, user.getName());
            statement.setObject(2, user.getUsername());
            statement.setObject(3, user.getPassword());
            statement.setObject(4, user.getAlamat());
            statement.setObject(5, user.getNoHp());
            statement.setObject(6, user.getJenisKelamin());
            statement.setObject(7, user.getSaldo());

            statement.executeUpdate();
        }
    }

    @Override
    public void search(Long id) {

    }

    @Override
    public String loginUser(String username, String password) {
        String name = null;
        String sql = """
                select (nama)  from user 
                where username = ? and password = ?;
                """;

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, username);
            statement.setString(2, password);

            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    name = resultSet.getString("nama");
                    return name;
                }else {
                    return null;
                }
            }

        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean login(String username, String password)  {
        String sql = """
                select *  from user 
                where username = ? and password = ?;
                """;

        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, username);
            statement.setString(2, password);

            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    return true;
                }else {
                    return false;
                }
            }

        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<User> searchList(String name) {
        return null;
    }
}
