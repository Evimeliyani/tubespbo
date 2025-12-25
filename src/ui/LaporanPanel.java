package ui;

import javax.swing.*;
import java.awt.*;

public class LaporanPanel extends JPanel {
    // Label untuk menampilkan angka
    private JLabel lblTotal = new JLabel("0");
    private JLabel lblHadir = new JLabel("0");
    private JLabel lblIzinSakit = new JLabel("0");

    public LaporanPanel() {
        // Desain UI sesuai gambar kamu
        // ... kode inisialisasi StatCard kamu di sini ...
    }

    // Method yang dipanggil oleh Controller
    public void setStatistik(int total, int hadir, int izinSakit) {
        lblTotal.setText(String.valueOf(total));
        lblHadir.setText(String.valueOf(hadir));
        lblIzinSakit.setText(String.valueOf(izinSakit));
    }
}