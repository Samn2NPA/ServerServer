import Common.Log;
import Server.HTTPServer;
import Server.SimpleTCPServer;

/**
 * Created by Phanh on 11/5/20.
 */
public class RunServer {
    static Log log = new Log(RunServer.class.getSimpleName());

    public static void main(String[] args) {
       /* HTTPServer httpServer = new HTTPServer();
        log.info(" HTTP server processing...");
        httpServer.start();

        SimpleTCPServer tcpServer = new SimpleTCPServer();
        log.info(" SimpleTCPServer processing...");*/

        Database myDb = new Database();
        try {
            myDb.readDataBase();
            log.info("start ok");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("EXCEPTION occurs");
        }
    }
}
