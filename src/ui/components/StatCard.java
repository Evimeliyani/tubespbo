package ui.components;

import javax.swing.*;
import java.awt.*;

public class StatCard extends JPanel {

    public StatCard(String number, String text, Color bg) {
        setPreferredSize(new Dimension(220, 120));
        setBackground(bg);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(new Color(220,220,220)));

        JLabel lblNumber = new JLabel(number);
        lblNumber.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblNumber.setBorder(BorderFactory.createEmptyBorder(20,20,0,0));

        JLabel lblText = new JLabel(text);
        lblText.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblText.setBorder(BorderFactory.createEmptyBorder(0,20,20,0));

        add(lblNumber, BorderLayout.CENTER);
        add(lblText, BorderLayout.SOUTH);
    }
}
