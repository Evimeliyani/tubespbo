package app;

import ui.MainFrame;
import websocket.SocketServer;
import javax.swing.SwingUtilities;

public class MainApp {
    public static void main(String[] args) {
        // JALANKAN SERVER TERLEBIH DAHULU DI BACKGROUND
        new Thread(() -> {
            new SocketServer().run();
        }).start();

        // JALANKAN UI
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}