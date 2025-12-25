package websocket;

import java.net.*;
import java.io.*;

public class SocketClient {
    public void send(String msg) {
        try {
            Socket s = new Socket("localhost",8888);
            new PrintWriter(s.getOutputStream(),true).println(msg);
        } catch(Exception e){}
    }
}
