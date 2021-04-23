package polis.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyLoader {

    private final Map<String, Properties> map;

    public PropertyLoader(){
        map = new HashMap<>();
        loadProperties("engine");
        loadProperties("levels");
    }

    public void loadProperties(String name){
        Properties properties = new Properties();
        String location = "/polis/" + name + ".properties";
        try (InputStream in = PropertyLoader.class.getResourceAsStream(location)) {
            properties.load(in);
        } catch (IOException ex) {
            System.out.println("Couldn't read: " + ex + " in " + location);
        }
        map.put(name,properties);
    }

    public String getProperty(String file, String name){
        return map.get(file).getProperty(name);
    }

}