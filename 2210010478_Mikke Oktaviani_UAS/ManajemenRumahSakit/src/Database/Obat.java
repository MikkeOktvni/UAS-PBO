/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import CRUD.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Obat {
    private int idObat;
    private int idPasien;
    private String namaPasien;
    private String dosis;

    public Obat() {
    }

    public Obat(int idObat, int idPasien, String namaPasien, String dosis) {
        this.idObat = idObat;
        this.idPasien = idPasien;
        this.namaPasien = namaPasien;
        this.dosis = dosis;
    }

    public Obat(int idPasien, String namaPasien, String dosis) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Metode untuk menambahkan obat ke database
    public void tambahObat() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Koneksi.getConnection();
            String query = "INSERT INTO obat (idObat, idPasien, namaPasien, dosis) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setInt(1, this.idObat);
            ps.setInt(2, this.idPasien);
            ps.setString(3, this.namaPasien);
            ps.setString(4, this.dosis);
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    // Metode untuk mengambil semua data obat dari database
    public static List<Obat> semuaObat() throws SQLException {
        List<Obat> daftarObat = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = Koneksi.getConnection();
            String query = "SELECT * FROM obat";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Obat obat = new Obat();
                obat.setIdObat(rs.getInt("idObat"));
                obat.setIdPasien(rs.getInt("idPasien"));
                obat.setNamaPasien(rs.getString("NamaPasien"));
                obat.setDosis(rs.getString("dosis"));
                daftarObat.add(obat);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return daftarObat;
    }

    // Getter dan Setter
    public int getIdObat() {
        return idObat;
    }


    public int getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(int idPasien) {
        this.idPasien = idPasien;
    }

    public String getNamaPasien() {
        return namaPasien;
    }


    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    
    public void updateObat() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Koneksi.getConnection();
            String query = "UPDATE obat SET idPasien=?, namaPasien=?, dosis=? WHERE idObat=?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, this.idPasien);
            ps.setString(2, this.namaPasien);
            ps.setString(3, this.dosis);
            ps.setInt(4, this.idObat);
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    // Metode untuk menghapus data obat dari database berdasarkan idObat
    public static void hapusObat(int idObat) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Koneksi.getConnection();
            String query = "DELETE FROM obat WHERE idObat=?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, idObat);
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void setIdObat(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setNamaPasien(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

