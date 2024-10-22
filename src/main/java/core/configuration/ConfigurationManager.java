package core.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

public class ConfigurationManager {

    private static ConfigurationManager instance; // Singleton instance
    private final String configFilePath;
    private Map<String, Map<String, Object>> cachedConfig;

    // Private constructor to prevent external instantiation
    private ConfigurationManager(String configFilePath) {
        this.configFilePath = configFilePath;
        this.cachedConfig = loadConfigFile(); // Load config at instantiation
    }

    // Thread-safe method to get the singleton instance
    public static synchronized ConfigurationManager getInstance(String configFilePath) {
        if (instance == null) {
            instance = new ConfigurationManager(configFilePath);
        }
        return instance;
    }

    // Method to get the specific configuration profile (like emulator, real_device)
    public Map<String, Object> getConfig(String profile) {
        return cachedConfig.getOrDefault(profile, Collections.emptyMap());
    }

    // Load the configuration from the file at the first instantiation
    private Map<String, Map<String, Object>> loadConfigFile() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Fetch the configuration file from the classpath
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(configFilePath);
            if (inputStream == null) {
                throw new RuntimeException("Configuration file not found: " + configFilePath);
            }

            // Read the content of the InputStream and parse it
            return mapper.readValue(inputStream,
                    mapper.getTypeFactory().constructMapType(Map.class, String.class, Map.class));

        } catch (JsonMappingException e) {
            throw new RuntimeException("Failed to map configuration file to Map: " + configFilePath, e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to process JSON configuration: " + configFilePath, e);
        } catch (IOException e) {
            throw new RuntimeException("IO error reading configuration file: " + configFilePath, e);
        }
    }
}
