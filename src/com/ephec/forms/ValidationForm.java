package com.ephec.forms;

import com.ephec.utilities.FrameworkSupport;
import com.ephec.utilities.Validator;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by michaelsilvestre on 17/05/14.
 */
public abstract class ValidationForm {

    public String validateData(HttpServletRequest request, AtomicReference<Map<String, String>> erreursRef, String dataKey, extraCallback next, String... dataKey2) {
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
                next.doJob(dataKey, erreursRef);
            }
        } catch (Exception ex) {
            Map<String, String> erreurTmp = erreursRef.get();
            erreurTmp.put(dataKey, ex.getMessage());
            erreursRef.set(erreurTmp);
        }
        return result;
    }

    public interface extraCallback {
        void doJob(String dataKey, AtomicReference<Map<String, String>> erreursRef);
    }
}
