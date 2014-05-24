package be.ephec.utilities;

import javax.servlet.http.HttpServletRequest;

public class FrameworkSupport {

    public static String getTrimedValue(HttpServletRequest request, String pParamName) {
        String value = request.getParameter(pParamName);
        if (value == null || value.trim().length() == 0) {
            return "";
        }

        return value.trim();
    }
}
