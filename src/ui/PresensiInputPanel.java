package ui;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import presensi_2tier.PresensiCRUD;

public class PresensiInputPanel extends JPanel {
    private JTextField txtNama = new JTextField(20);
    private JTextField txtTgl = new JTextField(10);
    private JComboBox<String> cbStatus = new JComboBox<>(new String[]{"Hadir", "Izin", "Sakit"});
    private JButton btnSimpan = new JButton("Simpan");

    public PresensiInputPanel(PresensiDataPanel dataPanel) {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        txtTgl.setText(LocalDate.now().toString());

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5,5,5,5); g.fill = GridBagConstraints.HORIZONTAL;

        g.gridx=0; g.gridy=0; add(new JLabel("Nama:"), g);
        g.gridx=1; add(txtNama, g);
        g.gridx=0; g.gridy=1; add(new JLabel("Tanggal:"), g);
        g.gridx=1; add(txtTgl, g);
        g.gridx=0; g.gridy=2; add(new JLabel("Status:"), g);
        g.gridx=1; add(cbStatus, g);
        g.gridy=3; add(btnSimpan, g);

        btnSimpan.addActionListener(e -> {
            new PresensiCRUD().tambahPresensi(txtNama.getText(), txtTgl.getText(), (String)cbStatus.getSelectedItem());
            JOptionPane.showMessageDialog(this, "Data Tersimpan!");
            dataPanel.refreshTable(); // Refresh otomatis ke tabel
            txtNama.setText("");
        });
    }
}