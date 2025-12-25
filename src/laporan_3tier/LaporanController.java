package laporan_3tier;

import ui.LaporanPanel;

public class LaporanController {
    private LaporanService service;
    private LaporanPanel view;

    public LaporanController(LaporanPanel view) {
        this.view = view;
        this.service = new LaporanService();
    }

    // Fungsi untuk memperbarui angka-angka di LaporanPanel
    public void updateLaporan() {
        int total = service.getTotalPresensi();
        int hadir = service.getHadirHariIni();
        int izinSakit = service.getIzinSakit();

        // Mengirim data ke UI LaporanPanel
        view.setStatistik(total, hadir, izinSakit);
    }
}