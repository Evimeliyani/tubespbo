package presensi_2tier;
import java.sql.*;
import java.util.*;
import model.Presensi;
import util.DBConnection;

public class PresensiCRUD {
    public void tambahPresensi(String nama, String tgl, String status) {
        String sql = "INSERT INTO presensi (nama, tanggal, status) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nama); ps.setString(2, tgl); ps.setString(3, status);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List<Presensi> getAllPresensi() {
        List<Presensi> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection(); Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM presensi ORDER BY id DESC")) {
            while (rs.next()) {
                list.add(new Presensi(rs.getInt("id"), rs.getString("nama"), rs.getString("tanggal"), rs.getString("status")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public void updatePresensi(int id, String nama, String tgl, String status) {
        String sql = "UPDATE presensi SET nama=?, tanggal=?, status=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nama); ps.setString(2, tgl); ps.setString(3, status); ps.setInt(4, id);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void hapusPresensi(int id) {
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement("DELETE FROM presensi WHERE id=?")) {
            ps.setInt(1, id); ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}