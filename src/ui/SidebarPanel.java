package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SidebarPanel extends JPanel {
    private MainFrame mainFrame; // Referensi ke MainFrame

    public SidebarPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        
        // Atur tampilan sidebar
        setPreferredSize(new Dimension(200, 0));
        setBackground(new Color(44, 62, 80)); // Warna biru gelap elegan
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));

        // Tambahkan Tombol
        add(createSidebarButton("Input Presensi", "INPUT_PRESENSI"));
        add(createSidebarButton("Data Presensi", "DATA_PRESENSI"));
        add(createSidebarButton("Laporan", "LAPORAN"));
    }

    private JButton createSidebarButton(String text, String pageName) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(180, 40));
        btn.setFocusPainted(false);
        btn.setBackground(new Color(52, 73, 94));
        btn.setForeground(Color.WHITE);
        btn.setBorderPainted(false);

        // Aksi ketika tombol diklik
        btn.addActionListener(e -> mainFrame.showPage(pageName));
        
        return btn;
    }
}