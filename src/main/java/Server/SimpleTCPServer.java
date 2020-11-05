package Server;

import Common.Config;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleTCPServer {
    private static final String TAG = SimpleTCPServer.class.getSimpleName() + "|";

    public SimpleTCPServer() {

        try (ServerSocket serverSocket = new ServerSocket(Config.SOCKET_PORT)) {
            while (true) {

                try (Socket socket = serverSocket.accept()) {


                    System.out.println(TAG + " Start socket in 6868");

                    InputStream input = socket.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                    OutputStream output = socket.getOutputStream();
                    PrintWriter writer = new PrintWriter(output, true);


                    String text;

                    do {
                        text = reader.readLine();
                        String reverseText = new StringBuilder(text).reverse().toString();
                        writer.println("Server: " + reverseText);

                    } while (!text.equals("bye"));

                    socket.close();

                }
            }


        } catch (IOException e) {
            System.out.println(TAG + " Start socket in 6868 :: failed");
            e.printStackTrace();
        }
    }
}
