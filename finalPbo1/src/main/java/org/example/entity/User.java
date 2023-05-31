package org.example.entity;

public class User {

    private Integer id;
    private String name;

    private String username;

    private String password;

    public Integer getId() {
        return id;
    }

    private Integer saldo;

    public User() {
    }

    public User(String name, String username,
                String password, String noHp, String alamat,
                String jenisKelamin, Integer saldo) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.noHp = noHp;
        this.alamat = alamat;
        this.jenisKelamin = jenisKelamin;
        this.saldo = saldo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    private String noHp;

    private String alamat;

    private String jenisKelamin;

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }
}
