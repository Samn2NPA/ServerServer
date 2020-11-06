package Server;

import Client.TCPClient;
import Common.Config;
import Common.Log;
import Common.Utils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

import static com.sun.net.httpserver.HttpServer.create;

/**
 * Created by Phanh on 11/5/20.
 */
public class HTTPServer {
    Log log = new Log(HTTPServer.class.getSimpleName());
    private HttpServer httpServer;
    private TCPClient tcpClient;


    public HTTPServer() {
        try {
            httpServer = create(new InetSocketAddress(Config.HTTP_PORT), 0);

            String strContext = "/login";
            httpServer.createContext(strContext, new HttpHandler() {
                public void handle(HttpExchange httpExchange) throws IOException {
                    log.info("calling to " + httpExchange.getLocalAddress() + "|" + httpExchange.getRequestURI());

                    log.info("call utils to get stream");
                    String strReqBody = Utils.getDataStream(httpExchange.getRequestBody());

                    log.info("Request: " + strReqBody);

                    //////////////////////-------------------------
                    tcpClient = new TCPClient(strReqBody);
                    //////////////////////-------------------------


                    //////////////////////-------------------------


                    String strRes = "This is response " + Utils.getDataStream(tcpClient.getSocketClient().getInputStream());
                    byte[] strResBytes = strRes.getBytes();
                    httpExchange.sendResponseHeaders(200, strResBytes.length);

                    OutputStream responseBody = httpExchange.getResponseBody();
                    responseBody.write(strResBytes);
                    responseBody.close();

                    log.info("Sent Response :: " + strRes);
                }
            });

            httpServer.setExecutor(null);


        } catch (IOException e) {
            log.info("EXCEPTION OCCURS");
            e.printStackTrace();
        }
    }

    public void start() {
        this.httpServer.start();
        log.info("start done.");
        log.info("Server is started and listening on port " + httpServer.getAddress().getPort());
    }

}
