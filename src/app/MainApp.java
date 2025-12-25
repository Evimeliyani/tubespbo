package app;

import javax.swing.UIManager;

import ui.MainFrame;
import websocket.SocketServer;

public class MainApp {
    public static void main(String[] args) {
        // Tambahkan ini di dalam main method MainApp.java sebelum memanggil MainFrame
try {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
} catch (Exception e) {
    e.printStackTrace();
}
        new SocketServer().start(); // websocket server
        new MainFrame().setVisible(true);
    }
}
