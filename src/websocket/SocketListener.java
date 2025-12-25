package websocket;

import java.net.*;
import java.io.*;

public class SocketListener extends Thread {
    Runnable onUpdate;
    public SocketListener(Runnable r){onUpdate=r;}

    public void run() {
        try {
            Socket s = new Socket("localhost",8888);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(s.getInputStream()));
            while(in.readLine()!=null) onUpdate.run();
        } catch(Exception e){}
    }
}
