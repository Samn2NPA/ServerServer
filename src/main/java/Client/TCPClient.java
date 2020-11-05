package Client;

import Common.Config;
import Server.SimpleTCPServer;

import java.io.IOException;
import java.net.Socket;

public class TCPClient {
    private static final String TAG = TCPClient.class.getSimpleName() + "|";

    private Socket socketClient;

    public TCPClient() {
        try {
            socketClient = new Socket(Config.SOCKET_IP, Config.SOCKET_PORT);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
