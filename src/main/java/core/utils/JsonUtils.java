package core.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JsonUtils {

    // Reuse the same ObjectMapper instance for all operations
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Cache to store parsed JSON data (Optional, if you want to avoid parsing multiple times)
    private static final Map<String, Object> cache = new ConcurrentHashMap<>();

    // Optimized method to parse JSON into Map<String, T>
    public static <T> Map<String, T> parseJson(String filePath, Class<T> clazz) throws IOException {
        // Check the cache first (optional)
        if (cache.containsKey(filePath)) {
            return (Map<String, T>) cache.get(filePath);
        }

        try (InputStream inputStream = JsonUtils.class.getClassLoader().getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new IOException("File not found: " + filePath);
            }

            // Parse the JSON into a map
            Map<String, T> result = objectMapper.readValue(
                    inputStream,
                    objectMapper.getTypeFactory().constructMapType(Map.class, String.class, clazz)
            );

            // Store in cache for future use (optional)
            cache.put(filePath, result);
            return result;
        }
    }

    // Method to clear the cache (Optional, useful for tests)
    public static void clearCache() {
        cache.clear();
    }
}
