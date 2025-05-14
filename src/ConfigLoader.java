import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {

    private static final Properties props = new Properties();

    static {
        try {
            props.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível carregar config.properties");
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
