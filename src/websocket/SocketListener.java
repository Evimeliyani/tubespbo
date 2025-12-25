package websocket;

import java.net.*;
import java.io.*;

public class SocketListener extends Thread {
    private Runnable onUpdate;
    public SocketListener(Runnable r) { this.onUpdate = r; }

    public void run() {
        while (true) {
            try (Socket s = new Socket("localhost", 8888);
                 BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    if (line.equals("REFRESH_TABLE")) {
                        onUpdate.run();
                    }
                }
            } catch (Exception e) {
                // Jika server mati, coba hubungkan kembali setiap 2 detik
                try { Thread.sleep(2000); } catch (Exception ex) {} 
            }
        }
    }
}