package ui;

import javax.swing.*;
import java.awt.*;
import laporan_3tier.LaporanController;

public class MainFrame extends JFrame {
    private JPanel mainContent; // Panel kanan yang isinya ganti-ganti
    private CardLayout cardLayout;

    public MainFrame() {
        setTitle("Sistem Presensi Karyawan");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 1. Sidebar (Kiri)
        // SidebarPanel membutuhkan referensi MainFrame ini untuk fungsi showPage
        SidebarPanel sidebar = new SidebarPanel(this);
        add(sidebar, BorderLayout.WEST);

        // 2. Main Content (Kanan) menggunakan CardLayout
        cardLayout = new CardLayout();
        mainContent = new JPanel(cardLayout);
        
        // Tambahkan panel-panel ke cardLayout sebagai komponen awal
        mainContent.add(new PresensiInputPanel(), "INPUT_PRESENSI");
        mainContent.add(new PresensiDataPanel(), "DATA_PRESENSI");
        
        // Untuk Laporan, kita siapkan instance awal
        LaporanPanel laporanPanel = new LaporanPanel();
        mainContent.add(laporanPanel, "LAPORAN");

        add(mainContent, BorderLayout.CENTER);
    }

    /**
     * Fungsi untuk pindah halaman yang dipanggil dari Sidebar.
     * Khusus untuk halaman LAPORAN, kita akan memanggil Controller 
     * agar data selalu yang terbaru saat halaman dibuka.
     */
    public void showPage(String pageName) {
        if (pageName.equals("LAPORAN")) {
            // Kita buat panel baru agar data selalu segar (fresh) dari database
            LaporanPanel lp = new LaporanPanel();
            LaporanController controller = new LaporanController(lp);
            
            // Panggil fungsi controller untuk mengisi data ke view
            controller.updateLaporan(); 
            
            // Update isi mainContent khusus untuk card LAPORAN
            mainContent.add(lp, "LAPORAN");
        }
        
        // Pindah ke halaman yang dipilih
        cardLayout.show(mainContent, pageName);
    }
}