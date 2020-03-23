package com.tel_ran;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class PropertiesService {
    private String prorsPath;
    private Properties props;

    public PropertiesService(String prorsPath) throws IOException {
        this.prorsPath = prorsPath;
        props = new Properties();
        props.load(new FileReader("config/config.props"));
    }

    public String getOperationPackage() {
        return props.getProperty("operations_package");
    }

    public List<String> getOperationNames() {
        String operations = props.getProperty("operations");
        return Arrays.asList(operations.split(","));
    }

    public List<String> getOperationPaths() {
        String packageName = getOperationPackage();
        List<String> names = getOperationNames();

        List<String> res = new ArrayList<>();
        for (String name : names) {
            res.add(packageName + "." + name);
        }

        return res;
    }
}
