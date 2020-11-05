import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;

import static com.sun.net.httpserver.HttpServer.create;

/**
 * Created by Phanh on 11/5/20.
 */
public class HTTPServer {
    private static final String TAG = HTTPServer.class.getSimpleName() + "|";
    private HttpServer httpServer;

    public HTTPServer() {
        try {
            httpServer = create(new InetSocketAddress(8989), 0);

            String strContext = "/login";
            httpServer.createContext(strContext, new HttpHandler() {
                public void handle(HttpExchange httpExchange) throws IOException {
                    System.out.println(TAG + "createContext|" + httpExchange.getLocalAddress() + "|" + httpExchange.getRequestURI());

                    InputStream inputStream = httpExchange.getRequestBody();

                    InputStreamReader streamReader = new InputStreamReader(inputStream);

                    BufferedReader bufferedReader = new BufferedReader(streamReader);

                    StringBuilder strBuffer = new StringBuilder();
                    String strLine;

                    while ((strLine = bufferedReader.readLine()) != null) {
                        strBuffer.append(strLine);
                    }

                    System.out.println(TAG + "createContext| GET REQUEST: " + strBuffer.toString());


                    OutputStream responseBody = httpExchange.getResponseBody();
                    String strRes = "This is response";
                    byte[] strResBytes = strRes.getBytes();
                    responseBody.write(strResBytes);

                    httpExchange.sendResponseHeaders(200, strResBytes.length);
                    responseBody.close();

                    System.out.println(TAG + "createContext| Sent Response");
                }
            });

            httpServer.setExecutor(null);


        } catch (IOException e) {
            System.out.println(TAG + "EXCEPTION OCCURS");
            e.printStackTrace();
        }
    }

    public void start() {
        this.httpServer.start();
        System.out.println(TAG + "start done.");
        System.out.println(TAG + "Server is started and listening on port "+ httpServer.getAddress().getPort());
    }

}
