package Server;

import Common.Config;
import Common.Log;
import Common.Utils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleTCPServer {
    Log log = new Log(SimpleTCPServer.class.getSimpleName());

    public SimpleTCPServer() {

        try (ServerSocket serverSocket = new ServerSocket(Config.SOCKET_PORT)) {
            log.info(" Started socket in port: " + Config.SOCKET_PORT);

            try (Socket socket = serverSocket.accept()) {
                log.info("socket is listening in port: " + Config.SOCKET_PORT);

                log.info("processing stream");

                InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(streamReader);

                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                log.info("handle data input");

                String text;
                text = bufferedReader.readLine();
                writer.println(" -- Server received: " + text);
                socket.close();
            }


        } catch (IOException e) {
            log.info(" Failed to Start socket in " + Config.SOCKET_PORT);
            e.printStackTrace();
        }
    }
}
