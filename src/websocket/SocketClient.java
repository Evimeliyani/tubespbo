package websocket;

import java.net.*;
import java.io.*;

public class SocketClient {
    public void send(String msg) {
        try (Socket s = new Socket("localhost", 8888);
             PrintWriter out = new PrintWriter(s.getOutputStream(), true)) {
            out.println(msg);
        } catch (Exception e) {
            System.err.println("Gagal kirim ke server: " + e.getMessage());
        }
    }
}