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

public class Pasien {
    private int idPasien;
    private String nama;
    private int umur;
    private String jenisKelamin;
    private String alamat;

    public Pasien() {
    }

    public Pasien(String nama, int umur, String jenisKelamin, String alamat) {
        this.nama = nama;
        this.umur = umur;
        this.jenisKelamin = jenisKelamin;
        this.alamat = alamat;
    }

    // Metode untuk menambahkan pasien ke database
    public void tambahPasien() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Koneksi.getConnection();
            String query = "INSERT INTO pasien (nama, umur, jenis_kelamin, alamat) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, this.nama);
            ps.setInt(2, this.umur);
            ps.setString(3, this.jenisKelamin);
            ps.setString(4, this.alamat);
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

    // Metode untuk mengambil semua data pasien dari database
    public static List<Pasien> semuaPasien() throws SQLException {
        List<Pasien> daftarPasien = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = Koneksi.getConnection();
            String query = "SELECT * FROM pasien";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Pasien pasien = new Pasien();
                pasien.setIdPasien(rs.getInt("id_pasien"));
                pasien.setNama(rs.getString("nama"));
                pasien.setUmur(rs.getInt("umur"));
                pasien.setJenisKelamin(rs.getString("jenis_kelamin"));
                pasien.setAlamat(rs.getString("alamat"));
                daftarPasien.add(pasien);
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
        return daftarPasien;
    }

    // Getter dan Setter
    public int getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(int idPasien) {
        this.idPasien = idPasien;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
    public void updatePasien() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Koneksi.getConnection();
            String query = "UPDATE pasien SET nama=?, umur=?, jenis_kelamin=?, alamat=? WHERE id_pasien=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, this.nama);
            ps.setInt(2, this.umur);
            ps.setString(3, this.jenisKelamin);
            ps.setString(4, this.alamat);
            ps.setInt(5, this.idPasien);
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

    // Metode untuk menghapus data pasien dari database berdasarkan id_pasien
    public static void hapusPasien(int idPasien) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Koneksi.getConnection();
            String query = "DELETE FROM pasien WHERE id_pasien=?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, idPasien);
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
}
