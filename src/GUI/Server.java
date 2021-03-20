/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author suha
 */
public class Server {

    private ServerSocket serverS = null;
    private Socket clientSocket = null;
    private List<ServeClient> listOfSocketClients = null;
    private int clientsCounter = 0;

    public Server() throws IOException {
        listOfSocketClients = Collections.synchronizedList(new ArrayList<ServeClient>());
        serverS = new ServerSocket(1900);
        accepClient();
    }

    void sendMessageForAll(String message) throws IOException {

        for (ServeClient sc : listOfSocketClients) {
            sc.sendMessege(message, clientsCounter);
        }
    }

    void accepClient() throws IOException {
        while (true) {
            clientSocket = serverS.accept();
            ServeClient client = new ServeClient(clientSocket, this);
            listOfSocketClients.add(client);
            clientsCounter = listOfSocketClients.size();
            client.start();
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
    }

}
