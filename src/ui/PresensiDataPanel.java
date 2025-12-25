package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import model.Presensi;
import presensi_2tier.PresensiCRUD;

public class PresensiDataPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private PresensiCRUD crud = new PresensiCRUD();

    public PresensiDataPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Header
        JLabel title = new JLabel("Manajemen Data Presensi");
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(title, BorderLayout.NORTH);

        // Tabel
        String[] cols = {"ID", "Nama", "Tanggal", "Status"};
        model = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(model);
        table.setRowHeight(30);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Panel Tombol di Bawah
        JPanel pnlTombol = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlTombol.setBackground(Color.WHITE);

        JButton btnEdit = new JButton("Edit Data");
        JButton btnHapus = new JButton("Hapus Data");
        JButton btnRefresh = new JButton("Refresh");

        // Styling Warna
        btnEdit.setBackground(new Color(52, 152, 219)); btnEdit.setForeground(Color.WHITE);
        btnHapus.setBackground(new Color(231, 76, 60)); btnHapus.setForeground(Color.WHITE);

        pnlTombol.add(btnRefresh);
        pnlTombol.add(btnEdit);
        pnlTombol.add(btnHapus);
        add(pnlTombol, BorderLayout.SOUTH);

        // LOGIKA TOMBOL
        btnRefresh.addActionListener(e -> refreshTable());

        btnHapus.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                int id = (int) table.getValueAt(row, 0);
                int konfirm = JOptionPane.showConfirmDialog(this, "Yakin hapus ID: " + id + "?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (konfirm == JOptionPane.YES_OPTION) {
                    crud.hapusPresensi(id);
                    refreshTable();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Pilih data dulu!");
            }
        });

        btnEdit.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                // Ambil data dari baris yang dipilih
                int id = (int) table.getValueAt(row, 0);
                String nama = (String) table.getValueAt(row, 1);
                String tgl = (String) table.getValueAt(row, 2);
                String status = (String) table.getValueAt(row, 3);

                // Panggil Dialog Edit Custom
                new EditDialog(id, nama, tgl, status).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Pilih data yang mau diedit!");
            }
        });

        refreshTable();
    }

    public void refreshTable() {
        model.setRowCount(0);
        for (Presensi p : crud.getAllPresensi()) {
            model.addRow(new Object[]{p.getId(), p.getNama(), p.getTanggal(), p.getStatus()});
        }
    }

    // --- INNER CLASS UNTUK POP-UP EDIT ---
    class EditDialog extends JDialog {
        private JTextField txtNama = new JTextField(20);
        private JComboBox<String> cbStatus = new JComboBox<>(new String[]{"Hadir", "Izin", "Sakit"});
        private JButton btnSimpan = new JButton("Update Data");

        public EditDialog(int id, String nama, String tgl, String status) {
            setTitle("Edit Presensi ID: " + id);
            setSize(350, 250);
            setLocationRelativeTo(null);
            setModal(true);
            setLayout(new GridLayout(4, 2, 10, 10));

            // Isi data lama ke form
            txtNama.setText(nama);
            cbStatus.setSelectedItem(status);

            add(new JLabel(" Nama:")); add(txtNama);
            add(new JLabel(" Status:")); add(cbStatus);
            add(new JLabel("")); // spacer
            add(btnSimpan);

            btnSimpan.addActionListener(e -> {
                crud.updatePresensi(id, txtNama.getText(), tgl, (String) cbStatus.getSelectedItem());
                JOptionPane.showMessageDialog(this, "Data Berhasil Diperbarui!");
                dispose();
                refreshTable(); // Refresh tabel setelah edit
            });
        }
    }
}