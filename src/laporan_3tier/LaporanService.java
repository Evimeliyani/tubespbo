package laporan_3tier;

import util.DBConnection;
import java.sql.*;

public class LaporanService {
    // Mengambil total semua data presensi
    public int getTotalPresensi() {
        return getCount("SELECT COUNT(*) FROM presensi");
    }

    // Mengambil jumlah yang statusnya 'Hadir'
    public int getHadirHariIni() {
        return getCount("SELECT COUNT(*) FROM presensi WHERE status = 'Hadir'");
    }

    // Mengambil jumlah gabungan Izin dan Sakit
    public int getIzinSakit() {
        return getCount("SELECT COUNT(*) FROM presensi WHERE status IN ('Izin', 'Sakit')");
    }

    private int getCount(String sql) {
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }
}