import Server.HTTPServer;

/**
 * Created by Phanh on 11/5/20.
 */
public class RunServer {

    private static final String TAG = RunServer.class.getSimpleName() + " -------   ";

    public static void main(String[] args) {
        HTTPServer httpServer = new HTTPServer();
        System.out.println(TAG + " HTTP server processing...");
        httpServer.start();
    }
}
