package core.utils;

import java.util.Map;

import org.testng.annotations.DataProvider;

public class DataUtils {

    /**
     * Generic DataProvider that reads a JSON file and returns a Map of key and DTO
     * instances.
     *
     * @param filePath the path to the JSON file
     * @param clazz    the DTO class to map the JSON data to
     * @param <T>      the type of the DTO class
     * @return an Object[][] for TestNG DataProvider
     * @throws Exception if there's an issue reading the JSON file
     */
    @DataProvider(name = "genericDataProvider")
    public static <T> Object[][] parseJsonData(String filePath, Class<T> clazz) throws Exception {
        // Parse the JSON file and return a map of T (DTO)
        Map<String, T> dataMap = JsonUtils.parseJson(filePath, clazz);

        // Convert the map to Object[][] for TestNG DataProvider
        return dataMap.entrySet().stream()
                .map(entry -> new Object[] { entry.getKey(), entry.getValue() })
                .toArray(Object[][]::new);
    }
}
