package be.ephec.utilities;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by michaelsilvestre on 17/05/14.
 */
public class FrameworkSupport {

    /**
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    public static String getTrimedValue(HttpServletRequest request, String pParamName) {
        String value = request.getParameter(pParamName);
        if (value == null || value.trim().length() == 0) {
            return "";
        }

        return value.trim();
    }
}
