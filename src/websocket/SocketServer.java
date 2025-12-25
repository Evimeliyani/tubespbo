package websocket;

import java.net.*;
import java.io.*;
import java.util.*;

public class SocketServer extends Thread {
    static List<Socket> clients = new ArrayList<>();

    public void run() {
        try (ServerSocket server = new ServerSocket(8888)) {
            while (true) {
                Socket s = server.accept();
                clients.add(s);
                new Client(s).start();
            }
        } catch (Exception e) {}
    }

    static void broadcast(String msg) {
        for (Socket s : clients) {
            try {
                new PrintWriter(s.getOutputStream(), true).println(msg);
            } catch (Exception e) {}
        }
    }

    static class Client extends Thread {
        Socket s;
        Client(Socket s){this.s=s;}
        public void run() {
            try {
                BufferedReader in = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));
                String msg;
                while((msg=in.readLine())!=null) broadcast(msg);
            } catch(Exception e){}
        }
    }
}
