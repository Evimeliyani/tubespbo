package ui;

import javax.swing.*;
import java.awt.*;

public class SidebarPanel extends JPanel {
    public SidebarPanel(MainFrame mainFrame) { // Harus ada parameter MainFrame
        setLayout(new GridLayout(10, 1, 5, 5));
        setPreferredSize(new Dimension(200, 0));

        JButton btnInput = new JButton("Input Presensi");
        JButton btnData = new JButton("Data Presensi");
        JButton btnLaporan = new JButton("Laporan");

        // Memanggil method tampilkanHalaman di MainFrame
        btnInput.addActionListener(e -> mainFrame.tampilkanHalaman("Input"));
        btnData.addActionListener(e -> mainFrame.tampilkanHalaman("Data"));
        btnLaporan.addActionListener(e -> mainFrame.tampilkanHalaman("Laporan"));

        add(btnInput);
        add(btnData);
        add(btnLaporan);
    }
}