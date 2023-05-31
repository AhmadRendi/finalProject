package org.example.repository;

import org.example.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface RepositoryUsers {

    List<User> viewList();

    void daftar(User user) throws SQLException;

    void search (Long id);

    List<User> searchList(String name);

    boolean login(String username, String password);
    String loginUser(String username, String password);

}
