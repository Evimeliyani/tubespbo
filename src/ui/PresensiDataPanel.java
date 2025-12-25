package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import model.Presensi;
import presensi_2tier.PresensiCRUD;
import websocket.SocketClient;
import websocket.SocketListener;

public class PresensiDataPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private PresensiCRUD crud = new PresensiCRUD();

    public PresensiDataPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        model = new DefaultTableModel(new String[]{"ID", "Nama", "Tanggal", "Status"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel pnlAction = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnEdit = new JButton("Ubah Status");
        JButton btnHapus = new JButton("Hapus");
        
        btnEdit.addActionListener(e -> aksiEditStatus());
        btnHapus.addActionListener(e -> aksiHapus());
        
        pnlAction.add(btnEdit);
        pnlAction.add(btnHapus);
        add(pnlAction, BorderLayout.SOUTH);

        // Menghubungkan Listener
        new SocketListener(this::refreshTable).start();
        refreshTable();
    }

    public void refreshTable() {
        SwingUtilities.invokeLater(() -> {
            model.setRowCount(0);
            for (Presensi p : crud.getAllPresensi()) {
                model.addRow(new Object[]{p.getId(), p.getNama(), p.getTanggal(), p.getStatus()});
            }
            System.out.println("Tampilan Tabel Di-refresh");
        });
    }

    private void aksiEditStatus() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data di tabel!");
            return;
        }
        
        // Ambil ID dari Kolom 0
        String id = table.getValueAt(row, 0).toString();
        String nama = table.getValueAt(row, 1).toString();
        String statusLama = table.getValueAt(row, 3).toString();

        String[] pilihan = {"Hadir", "Izin", "Sakit", "Alpa"};
        String statusBaru = (String) JOptionPane.showInputDialog(this, 
                "Ubah status " + nama + ":", "Edit Status",
                JOptionPane.QUESTION_MESSAGE, null, pilihan, statusLama);

        if (statusBaru != null && !statusBaru.equals(statusLama)) {
            System.out.println("Mengirim Update ID: " + id + " Status: " + statusBaru);
            new SocketClient().send("UPDATE_STATUS|" + id + "|" + statusBaru);
        }
    }

    private void aksiHapus() {
        int row = table.getSelectedRow();
        if (row == -1) return;
        String id = table.getValueAt(row, 0).toString();
        int confirm = JOptionPane.showConfirmDialog(this, "Hapus data?");
        if (confirm == JOptionPane.YES_OPTION) {
            new SocketClient().send("DELETE_PRESENSI|" + id);
        }
    }
}