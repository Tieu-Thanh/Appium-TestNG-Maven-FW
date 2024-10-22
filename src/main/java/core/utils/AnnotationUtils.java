package core.utils;

import java.lang.reflect.Method;
import java.util.Map;

import core.annotation.DataKey;

public class AnnotationUtils {
    // This method now uses the preloaded data instead of reading the file every time
    public <T> T getData(Method testMethod, Map<String, T> dataMap) throws Exception {
        if (testMethod.isAnnotationPresent(DataKey.class)) {
            // Get the dataKey from the annotation
            DataKey dataKeyAnnotation = testMethod.getAnnotation(DataKey.class);
            String dataKey = dataKeyAnnotation.value();

            // Return the data corresponding to the dataKey from the preloaded map
            return dataMap.get(dataKey);
        }
        return null; // If the annotation is not present, return null
    }
}
