package user;

import org.example.entity.User;
import org.example.repository.RepositoryUserImpl;
import org.example.repository.RepositoryUsers;
import org.junit.jupiter.api.Test;

import java.net.UnknownServiceException;
import java.sql.SQLException;

public class testUser {

    RepositoryUsers repositoryUsers = new RepositoryUserImpl();

    User user = new User();
    @Test
    void daftar() throws SQLException {

        String name = "ahmad";
        String username = "ahmad@gmail.com";
        String password = "123";
        String alamat = "makassar";
        String noHp = "0829";
        String jenKel = "pria";
        Integer saldo = 50_000_000;

        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        user.setAlamat(alamat);
        user.setNoHp(noHp);
        user.setJenisKelamin(jenKel);
        user.setSaldo(saldo);

        repositoryUsers.daftar(user);

    }
}
