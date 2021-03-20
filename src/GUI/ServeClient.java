/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author suha
 */
public class ServeClient extends Thread {

    private Server server;
    private Socket socket1 = null;
    private PrintWriter pw = null;
    private BufferedReader br = null;
    private InputStream is;
    private OutputStream os;

    public ServeClient(Socket socket, Server server) throws IOException {

        this.socket1 = socket;
        this.server = server;
        pw = new PrintWriter(socket1.getOutputStream(), true);
        br = new BufferedReader(new InputStreamReader(socket1.getInputStream()));

    }

    @Override
    public void run() {

        try {
            String input;
            while ((input = br.readLine()) != null) {
                server.sendMessageForAll(input);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    void sendMessege(String clientMessage, int count) throws IOException {

        if (clientMessage.equals("$")) {
            pw.println(count + " " + clientMessage);
            pw.flush();
        } else {
            if (clientMessage.contains("is left")) {
                count--;
                pw.println(count + " " + clientMessage);
                pw.flush();
            } else {
                    pw.println(clientMessage);
                    pw.flush();
                }
        }
    }
}
