package laporan_3tier;

import util.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LaporanDAO {

    public int getTotalPresensi() {
        try (Connection c = DBConnection.getConnection();
             Statement s = c.createStatement()) {

            ResultSet rs = s.executeQuery("SELECT COUNT(*) FROM presensi");
            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}