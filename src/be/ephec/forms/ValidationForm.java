package be.ephec.forms;

import be.ephec.utilities.FrameworkSupport;
import be.ephec.utilities.Validator;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by michaelsilvestre on 17/05/14.
 */
public abstract class ValidationForm {

    public static final String USER_SESSION = "userSession";
    private final Map<String, String> erreurs = new HashMap<>();
    private String result;

    public ValidationForm() {
    }

    public String validateData(HttpServletRequest request, String dataKey, extraCallback next, String... dataKey2) {
        String result = "";
        try {
            if (dataKey2.length > 0) {

                result = FrameworkSupport.getTrimedValue(request, dataKey);
                String value2 = FrameworkSupport.getTrimedValue(request, dataKey2[0]);
                Validator.class.getDeclaredMethod(dataKey + "Validation", String.class, String.class).invoke(null, result, value2);
            } else {
                result = FrameworkSupport.getTrimedValue(request, dataKey);
                Validator.class.getDeclaredMethod(dataKey + "Validation", String.class).invoke(null, result);
            }
            if (next != null) {
                next.doJob(dataKey, erreurs);
            }
        } catch (Exception ex) {
            erreurs.put(dataKey, ex.getMessage());
        }
        return result;
    }

    protected void setErreur(String champ, String body) {
        erreurs.put(champ, body);
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResult() {
        return result;
    }

    protected void setResult(String result) {
        this.result = result;
    }

    public interface extraCallback {
        void doJob(String dataKey, Map<String, String> erreurs);
    }
}
