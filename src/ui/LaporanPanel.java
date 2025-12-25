package ui;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class LaporanPanel extends JPanel {
    private JLabel lblTotal, lblHadir, lblIzinSakit;

    public LaporanPanel() {
        // Background utama abu-abu sangat muda agar Card putih terlihat menonjol
        setLayout(new BorderLayout());
        setBackground(new Color(248, 249, 250));
        setBorder(new EmptyBorder(30, 30, 30, 30));

        // Header Atas
        JLabel title = new JLabel("Dashboard Rekapitulasi Presensi");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(new Color(33, 37, 41));
        add(title, BorderLayout.NORTH);

        // Panel Tengah untuk menampung Card (3 kolom)
        JPanel container = new JPanel(new GridLayout(1, 3, 20, 0));
        container.setOpaque(false);

        // Membuat 3 Card Statistik
        lblTotal = createCard(container, "Total Seluruh Data", new Color(74, 144, 226));    // Biru
        lblHadir = createCard(container, "Karyawan Hadir", new Color(46, 204, 113));      // Hijau
        lblIzinSakit = createCard(container, "Izin atau Sakit", new Color(241, 196, 15)); // Kuning

        add(container, BorderLayout.CENTER);
    }

    // Fungsi untuk membuat desain Card yang rapi
    private JLabel createCard(JPanel parent, String title, Color accentColor) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
            new EmptyBorder(20, 20, 20, 20)
        ));

        // Judul kecil di atas
        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTitle.setForeground(new Color(108, 117, 125));
        card.add(lblTitle, BorderLayout.NORTH);

        // Angka besar di tengah
        JLabel lblValue = new JLabel("0");
        lblValue.setFont(new Font("Segoe UI", Font.BOLD, 48));
        lblValue.setForeground(accentColor);
        lblValue.setHorizontalAlignment(JLabel.CENTER);
        card.add(lblValue, BorderLayout.CENTER);

        // Aksen garis berwarna di bawah card agar elegan
        JPanel accent = new JPanel();
        accent.setPreferredSize(new Dimension(0, 5));
        accent.setBackground(accentColor);
        card.add(accent, BorderLayout.SOUTH);

        parent.add(card);
        return lblValue; // Mengembalikan label angka agar bisa diupdate
    }

    // Fungsi Update yang dipanggil Controller
    public void setStatistik(int total, int hadir, int izinSakit) {
        lblTotal.setText(String.valueOf(total));
        lblHadir.setText(String.valueOf(hadir));
        lblIzinSakit.setText(String.valueOf(izinSakit));
    }
}