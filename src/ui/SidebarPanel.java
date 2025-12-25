package ui;
import javax.swing.*;
import java.awt.*;

public class SidebarPanel extends JPanel {
    public SidebarPanel(MainFrame frame) {
        setLayout(new GridLayout(5, 1, 10, 10));
        setPreferredSize(new Dimension(200, 0));
        setBackground(new Color(236, 240, 241));

        String[] menus = {"Input Presensi", "Data Presensi", "Laporan"};
        for (String m : menus) {
            JButton btn = new JButton(m);
            btn.addActionListener(e -> frame.tampilkanHalaman(m.startsWith("Input") ? "Input" : m.startsWith("Data") ? "Data" : "Laporan"));
            add(btn);
        }
    }
}