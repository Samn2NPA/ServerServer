package Common;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

/**
 * Created by Phanh on 11/6/20.
 */
public class Log {

    private String TAG;
    private static final String templateTag = "[%s]";

    public Log(String className) {
        this.TAG = className;
    }

    public String getTag(String className) {
        return String.format(templateTag, className);
    }

    public void info(String message) {
        System.out.println(getTag(this.TAG) + ":: " + message);
    }
}
