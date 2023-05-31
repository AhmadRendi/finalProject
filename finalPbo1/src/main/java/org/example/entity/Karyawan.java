package org.example.entity;

public class Karyawan extends User{
    public Karyawan(String name, String username, String password,
                    String noHp, String alamat, String jenisKelamin,
                    Integer saldo) {
        super(name, username, password, noHp, alamat, jenisKelamin, saldo);
    }

    private Integer idKaryawan;

    public Integer getIdKaryawan() {
        return idKaryawan;
    }

    public void setIdKaryawan(Integer idKaryawan) {
        this.idKaryawan = idKaryawan;
    }
}
