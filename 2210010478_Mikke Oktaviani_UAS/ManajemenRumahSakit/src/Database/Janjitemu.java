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
/**
 *
 * @author HP
 */
public class Janjitemu {
    private int idJanjitemu;
    private int idPasien;
    private int idDokter;
    private String tanggalJanji;
    private String status;
    
    public Janjitemu() {
    }
    
    public Janjitemu(int idPasien, int idDokter, int idDokter2, String tanggalJanji, String status) {
        this.idPasien = idPasien;
        this.idDokter = idDokter;
        this.tanggalJanji = tanggalJanji;
        this.status = status;
    }
    
    // Metode untuk menambahkan temujanji ke database
    public void tambahJanjitemu() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Koneksi.getConnection();
            String query = "INSERT INTO janjitemu (idPasien, idDokter, tanggalJanji, status) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setInt(1, this.idPasien);
            ps.setInt(2, this.idDokter);
            ps.setString(3, this.tanggalJanji);
            ps.setString(4, this.status);
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
    
    // Metode untuk mengambil semua data temujanji dari database
    public static List<Janjitemu> semuaJanjitemu() throws SQLException {
        List<Janjitemu> daftarJanjitemu = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = Koneksi.getConnection();
            String query = "SELECT * FROM janjitemu";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Janjitemu Janjitemu = new Janjitemu();
                Janjitemu.setIdJanjitemu(rs.getInt("Janjitemu"));
                Janjitemu.setIdPasien(rs.getInt("IdPasien"));
                Janjitemu.setIdDokter(rs.getInt("IdDokter"));
                Janjitemu.setTanggalJanji(rs.getString("TanggalJanji"));
                Janjitemu.setStatus(rs.getString("status"));
                daftarJanjitemu.add(Janjitemu);
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
        return daftarJanjitemu;
    }
    
// Getter dan Setter
    public int getIdJanjitemu() {
        return idJanjitemu;
    }

    public void setIdJanjitemu(int idJanjitemu) {
        this.idJanjitemu = idJanjitemu;
    }

    public int getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(int idPasien) {
        this.idPasien = idPasien;
    }

    public int getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(int idDokter) {
        this.idDokter = idDokter;
    }

    public String getTanggalJanji() {
        return tanggalJanji;
    }

    public void setTanggalJanji(String tanggalJanji) {
        this.tanggalJanji = tanggalJanji;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public void updateJanjitemu() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Koneksi.getConnection();
            String query = "UPDATE janjitemu SET idPasien=?, idDokter=?, tanggalJanji=?, status=? WHERE idJanjitemu=?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, this.idPasien);
            ps.setInt(2, this.idDokter);
            ps.setString(3, this.tanggalJanji);
            ps.setString(4, this.status);
            ps.setInt(5, this.idJanjitemu);
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

    // Metode untuk menghapus data janjitemu dari database berdasarkan idJanjitemu
    public static void hapusJanjitemu(int idJanjitemu) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Koneksi.getConnection();
            String query = "DELETE FROM janjitemu WHERE idJanjitemu=?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, idJanjitemu);
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


