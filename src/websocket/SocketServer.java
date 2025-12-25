package websocket;

import java.net.*;
import java.io.*;
import java.util.*;
import util.DBConnection;
import java.sql.*;

public class SocketServer extends Thread {
    private static List<PrintWriter> clientWriters = new ArrayList<>();

    public void run() {
        // Gunakan port 8888. Jika error bind lagi, ganti ke 8889
        try (ServerSocket server = new ServerSocket(8888)) {
            System.out.println(">>> SERVER BERHASIL JALAN <<<");
            while (true) {
                Socket s = server.accept();
                PrintWriter writer = new PrintWriter(s.getOutputStream(), true);
                clientWriters.add(writer);
                new ClientHandler(s).start();
            }
        } catch (Exception e) { 
            System.out.println("SERVER GAGAL: " + e.getMessage());
            System.out.println("TIPS: Tutup semua aplikasi Java lalu jalankan ulang.");
        }
    }

    public static void broadcast(String msg) {
        for (PrintWriter out : clientWriters) {
            out.println(msg);
        }
    }

    static class ClientHandler extends Thread {
        private Socket s;
        ClientHandler(Socket s) { this.s = s; }

        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()))) {
                String msg;
                while ((msg = in.readLine()) != null) {
                    System.out.println("Server Menerima: " + msg);
                    
                    if (msg.startsWith("UPDATE_STATUS|")) {
                        String[] p = msg.split("\\|");
                        updateDB(p[1], p[2]);
                    } else if (msg.startsWith("SAVE_PRESENSI|")) {
                        saveDB(msg);
                    } else if (msg.startsWith("DELETE_PRESENSI|")) {
                        deleteDB(msg.split("\\|")[1]);
                    }
                    
                    broadcast("REFRESH_TABLE");
                }
            } catch (Exception e) { }
        }

        private void updateDB(String id, String status) {
            try (Connection conn = DBConnection.getConnection()) {
                String sql = "UPDATE presensi SET status=? WHERE id=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, status);
                ps.setInt(2, Integer.parseInt(id));
                int rows = ps.executeUpdate();
                System.out.println("SQL: Update ID " + id + " -> " + status + " (" + rows + " baris berubah)");
            } catch (Exception e) {
                System.out.println("SQL Error Update: " + e.getMessage());
            }
        }

        private void saveDB(String data) throws Exception {
            String[] p = data.split("\\|");
            try (Connection conn = DBConnection.getConnection()) {
                String sql = "INSERT INTO presensi (nama, tanggal, status) VALUES (?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, p[1]); ps.setString(2, p[2]); ps.setString(3, p[3]);
                ps.executeUpdate();
                System.out.println("SQL: Simpan Berhasil");
            }
        }

        private void deleteDB(String id) throws Exception {
            try (Connection conn = DBConnection.getConnection()) {
                String sql = "DELETE FROM presensi WHERE id=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(id));
                ps.executeUpdate();
                System.out.println("SQL: Hapus Berhasil");
            }
        }
    }
}