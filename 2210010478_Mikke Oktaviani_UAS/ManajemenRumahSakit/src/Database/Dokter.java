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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author HP
 */
public class Dokter {
    private int idDokter;
    private String nama;
    private String spesialis;
    private String telepon;

    public Dokter (int aInt, String string, String string1, String string2){
    
    }
    
    public Dokter(String nama, String spesialis, String telepon) {
        this.nama = nama;
        this.spesialis = spesialis;
        this.telepon = telepon;
    }

    public Dokter() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    // Metode untuk menambahkan dokter ke database
    public void simpanDokter() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Koneksi.getConnection();
            String query = "INSERT INTO dokter (nama, spesialis, telepon) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, this.nama);
            ps.setString(2, this.spesialis);
            ps.setString(3, this.telepon);
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

    // Metode untuk mengambil semua data dokter dari database
    public static List<Dokter> semuaDokter() throws SQLException {
        List<Dokter> daftarDokter = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = Koneksi.getConnection();
            String query = "SELECT * FROM dokter";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Dokter dokter = new Dokter(rs.getInt("idDokter"), rs.getString("nama"), rs.getString("spesialisasi"), rs.getString("telepon"));
                dokter.setIdDokter(rs.getInt("idDokter"));
                dokter.setNama(rs.getString("nama"));
                dokter.setSpesialis (rs.getString("spesialis"));
                dokter.setTelepon(rs.getString("telepon"));
                daftarDokter.add(dokter);
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
        return daftarDokter;
    }

    // Getter dan Setter
    public int getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(int idDokter) {
        this.idDokter = idDokter;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSpesialis() {
        return spesialis;
    }

    public void setSpesialis(String spesialis) {
        this.spesialis = spesialis;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    
    public void ubahDokter() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Koneksi.getConnection();
            String query = "UPDATE dokter SET nama=?, spesialis=?, telepon=? WHERE idDokter=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, this.nama);
            ps.setString(2, this.spesialis);
            ps.setString(3, this.telepon);
            ps.setInt(4, this.idDokter);
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

    // Metode untuk menghapus data dokter dari database berdasarkan idDokter
    public static void hapusDokter(int idDokter) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Koneksi.getConnection();
            String query = "DELETE FROM dokter WHERE idDokter=?";
            ps = conn.prepareStatement(query);
            ps.setInt(4, idDokter);
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

public List<Dokter> tampilkanDataDokter() throws SQLException {
        String query = "SELECT * FROM Dokter";
        List<Dokter> dokterList = new ArrayList<>();
        try (Connection con = Koneksi.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                Dokter dokter = new Dokter (rs.getInt("idDokter"), rs.getString("nama"), rs.getString("spesialisasi"), rs.getString("telepon"));
                dokterList.add(dokter);
            }
        }
        return dokterList;
    }
}