package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);
    
    // Inisialisasi panel-panel halaman
    private PresensiDataPanel presensiDataPanel = new PresensiDataPanel();
    private PresensiInputPanel presensiInputPanel = new PresensiInputPanel(presensiDataPanel);
    private LaporanPanel laporanPanel = new LaporanPanel();
    private SidebarPanel sidebarPanel;

    public MainFrame() {
        setTitle("Sistem Presensi Karyawan");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Mengirimkan 'this' (MainFrame) ke SidebarPanel agar tombol bisa bekerja
        sidebarPanel = new SidebarPanel(this);

        mainPanel.add(presensiInputPanel, "Input");
        mainPanel.add(presensiDataPanel, "Data");
        mainPanel.add(laporanPanel, "Laporan");

        add(sidebarPanel, BorderLayout.WEST); // Sidebar di kiri
        add(mainPanel, BorderLayout.CENTER); // Konten di kanan
    }

    public void tampilkanHalaman(String namaHalaman) {
        if (namaHalaman.equals("Data")) {
            presensiDataPanel.refreshTable(); // Refresh tabel saat halaman dibuka
        }
        cardLayout.show(mainPanel, namaHalaman);
    }
}