package be.ephec.utilities;

import com.mysql.jdbc.ResultSetMetaData;

import java.lang.reflect.Field;
import java.sql.ResultSet;

public class EntityMapping<T> {
    private final Class<T> t;

    public EntityMapping(Class<T> pT) {
        t = pT;
    }

    public T getMapping(ResultSet pRelSet) throws Exception {
        ResultSetMetaData md = (ResultSetMetaData) pRelSet.getMetaData();
        int columns = md.getColumnCount();
        T objT = t.newInstance();
        for (int i = 1; i <= columns; ++i) {
            String columnName = md.getColumnLabel(i);
            try {
                String methodName = "set" + Character.toUpperCase(columnName.charAt(0)) + columnName.substring(1);
                String FieldName = Character.toLowerCase(columnName.charAt(0)) + columnName.substring(1);
                Field field = objT.getClass().getDeclaredField(FieldName);
                if (field.getType() == int.class || field.getType() == Integer.class) {
                    objT.getClass().getDeclaredMethod(methodName, field.getType()).invoke(objT, pRelSet.getInt(columnName));
                } else if (field.getType() == String.class) {
                    objT.getClass().getDeclaredMethod(methodName, field.getType()).invoke(objT, pRelSet.getString(columnName));
                }
            } catch (Exception ex) {
                throw new Exception("Impossible to map the field " + columnName);
            }
        }
        return objT;
    }
}
