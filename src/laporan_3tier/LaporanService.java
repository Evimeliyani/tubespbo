package laporan_3tier;

import java.sql.*;
import util.DBConnection;

public class LaporanService {
    public int getTotalPresensi() {
        return executeCountQuery("SELECT COUNT(*) FROM presensi");
    }

    public int getHadirHariIni() {
        // Query untuk menghitung status 'Hadir' hari ini
        return executeCountQuery("SELECT COUNT(*) FROM presensi WHERE status='Hadir'");
    }

    public int getIzinSakit() {
        // Query untuk menghitung status 'Izin' atau 'Sakit'
        return executeCountQuery("SELECT COUNT(*) FROM presensi WHERE status IN ('Izin', 'Sakit')");
    }

    private int executeCountQuery(String sql) {
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }
}