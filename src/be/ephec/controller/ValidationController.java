package be.ephec.controller;

import be.ephec.exceptions.ValidatorException;
import be.ephec.utilities.FrameworkSupport;
import be.ephec.utilities.Validator;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public abstract class ValidationController {

    protected static final String USER_SESSION = "userSession";
    private final Map<String, String> erreurs = new HashMap<>();
    private String result;

    public ValidationController() {
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
        } catch (ValidatorException e) {
            erreurs.put(dataKey, e.getMessage());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            erreurs.put(dataKey, e.getTargetException().getMessage());
        } catch (NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
            erreurs.put(dataKey, e.getMessage());
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

    protected interface extraCallback {
        void doJob(String dataKey, Map<String, String> erreurs);
    }
}
