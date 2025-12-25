package ui;

import net.miginfocom.swing.MigLayout;
import websocket.SocketClient;
import javax.swing.*;
import java.awt.*;

public class PresensiInputPanel extends JPanel {
    private JTextField txtNama, txtTanggal;
    private JComboBox<String> cbStatus;

    public PresensiInputPanel() {
        setBackground(Color.WHITE);
        setLayout(new MigLayout("wrap 1, fillx, insets 40", "[fill]"));

        JLabel lblTitle = new JLabel("Input Presensi");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        add(lblTitle, "gapbottom 20");

        add(new JLabel("Nama Lengkap"), "gapbottom 5");
        txtNama = new JTextField();
        add(txtNama, "height 40!, gapbottom 20");

        add(new JLabel("Tanggal (YYYY-MM-DD)"), "gapbottom 5");
        txtTanggal = new JTextField("2025-12-25");
        add(txtTanggal, "height 40!, gapbottom 20");

        add(new JLabel("Status"), "gapbottom 5");
        cbStatus = new JComboBox<>(new String[]{"Hadir", "Izin", "Sakit", "Alpa"});
        add(cbStatus, "height 40!, gapbottom 40");

        JButton btnSimpan = new JButton("Simpan ke Server");
        btnSimpan.setBackground(new Color(13, 110, 253));
        btnSimpan.setForeground(Color.WHITE);
        btnSimpan.setFont(new Font("Segoe UI", Font.BOLD, 15));

        btnSimpan.addActionListener(e -> aksiSimpan());
        add(btnSimpan, "height 45!");
    }

    private void aksiSimpan() {
        String nama = txtNama.getText().trim();
        String tgl = txtTanggal.getText().trim();
        String status = (String) cbStatus.getSelectedItem();

        if (nama.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama harus diisi!");
            return;
        }

        // Protokol 3-Tier: Mengirim data mentah ke server aplikasi
        String protocolMsg = "SAVE_PRESENSI|" + nama + "|" + tgl + "|" + status;
        new SocketClient().send(protocolMsg);

        JOptionPane.showMessageDialog(this, "Data dikirim ke server aplikasi!");
        txtNama.setText("");
    }
}