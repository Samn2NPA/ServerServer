package Client;

import Common.Config;
import Common.Log;
import Common.Utils;
import Server.SimpleTCPServer;

import java.io.*;
import java.net.Socket;

public class TCPClient {
    Log log = new Log(TCPClient.class.getSimpleName());

    private Socket socketClient;

    public TCPClient(String req) {
        try {
            socketClient = new Socket(Config.SOCKET_IP, Config.SOCKET_PORT);

            log.info("create socket client ok --- port " + socketClient.getPort());

            OutputStream outputStream = socketClient.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);
            writer.println(req);

            log.info("sent msg ok.");

            ////////////-------------------
           /* log.info("call utils to get stream");
            String inputData = Utils.getDataStream(socketClient.getInputStream());
            log.info("Response from server: " + inputData);*/

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocketClient() {
        return socketClient;
    }

    public Socket getResponseSocket() {

        OutputStream outputStream;
        try {
            outputStream = socketClient.getOutputStream();
            String outMsg = "this is msg from Socket Client --- port " + socketClient.getPort();
            outputStream.write(outMsg.getBytes());

            PrintWriter writer = new PrintWriter(outputStream, true);
            writer.write("end request.");
            //   writer.println(" -- Phanh Phanh");

            log.info("sent msg ok.");

            ////////////-------------------
            log.info("call utils to get stream");
            String inputData = Utils.getDataStream(socketClient.getInputStream());
            log.info("Response from server: " + inputData);

        } catch (IOException e) {
            e.printStackTrace();
            log.info("EXCEPTION");
        }

        log.info("Return socketClient");
        return this.socketClient;
    }


}
