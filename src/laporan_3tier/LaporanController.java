package laporan_3tier;

import ui.LaporanPanel;

public class LaporanController {
    private LaporanService service;
    private LaporanPanel view;

    public LaporanController(LaporanPanel view) {
        this.view = view;
        this.service = new LaporanService();
    }

    public void updateLaporan() {
        // Ambil data dari service
        int total = service.getTotalPresensi();
        int hadir = service.getHadirHariIni();
        int izinSakit = service.getIzinSakit();

        // Kirim data ke UI
        view.setStatistik(total, hadir, izinSakit);
    }
}