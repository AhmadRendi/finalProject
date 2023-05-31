package org.example.service;

import org.example.entity.User;
import org.example.repository.RepositoryUserImpl;
import org.example.repository.RepositoryUsers;
import org.example.view.DaftarView;
import org.example.view.HomeAnggota;
import org.example.view.ViewLogin;
import org.example.view.ViewPenjualan;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class ServiceUser {


    private DaftarView daftar;
    private ViewLogin viewLogin;
    private RepositoryUsers repositoryUsers;

    private List<User> list;

    public ServiceUser() {
    }

    public ServiceUser(DaftarView daftar) {
        this.daftar = daftar;
        this.repositoryUsers = new RepositoryUserImpl();
    }

    public ServiceUser(ViewLogin viewLogin){
        this.viewLogin = viewLogin;
        this.repositoryUsers = new RepositoryUserImpl();
    }

    public String jenKel(){
        ButtonGroup buttonGroup = new ButtonGroup();

        buttonGroup.add(daftar.getRadioPria());
        buttonGroup.add(daftar.getRadioWanita());

        if(daftar.getRadioPria().isSelected()){
            return daftar.getRadioPria().getText();
        }else {
            return daftar.getRadioWanita().getText();
        }
    }

    public void addUser() throws SQLException {
        User user = new User();

        user.setName(daftar.getTextName().getText());
        user.setUsername(daftar.getTextUsername().getText());
        user.setPassword(daftar.getTextPassword().getText());
        user.setAlamat(daftar.getTextAlamat().getText());
        user.setNoHp(daftar.getTextNoHand().getText());
        user.setJenisKelamin(jenKel());
        user.setSaldo(Integer.valueOf(daftar.getTextSaldo().getText()));
        JOptionPane.showMessageDialog(null, "Data Berhasil Di Simpan");
        repositoryUsers.daftar(user);
    }

    public void reset(){
        daftar.getTextName().setText("");
        daftar.getTextUsername().setText("");
        daftar.getTextPassword().setText("");
        daftar.getTextAlamat().setText("");
        daftar.getTextNoHand().setText("");
         daftar.getTextSaldo().setText("");
    }

//    public String namesHome(String username, String password){
//        String names = repositoryUsers.loginUser(username, password);
//        return names;
//    }

    public void login() throws SQLException {
        if(repositoryUsers.login(viewLogin.getTextUsername().getText(),
                viewLogin.getTextPassword().getText())){
            String names = repositoryUsers.loginUser(viewLogin.getTextUsername().getText(),
                    viewLogin.getTextPassword().getText());
            HomeAnggota homeAnggota = new HomeAnggota();
            homeAnggota.setVisible(true);
            viewLogin.setVisible(false);
            homeAnggota.getTlbNameHome().setText(names);
        }else {
            JOptionPane.showMessageDialog(null, "gagal Login");
        }
    }

}
