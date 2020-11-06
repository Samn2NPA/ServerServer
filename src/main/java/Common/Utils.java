package Common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Phanh on 11/6/20.
 */
public class Utils {
    static Log log = new Log(Utils.class.getSimpleName());

    public static String getDataStream(InputStream inputStream) {
        StringBuilder strBuilder = new StringBuilder();
        try {
            InputStreamReader streamReader = new InputStreamReader(inputStream);

            BufferedReader bufferedReader = new BufferedReader(streamReader);


            String strLine;

            while ((strLine = bufferedReader.readLine()) != null && !strLine.isEmpty()) {
                log.info("write line :: " + strLine);
                strBuilder.append(strLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strBuilder.toString();
    }
}
