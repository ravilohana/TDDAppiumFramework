package com.qa.mobile.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class MobileProperties {

    Properties props = new Properties();
    FileInputStream propsFile;
    String configPropsPath = System.getProperty("user.dir") + File.separator + "src" + File.separator +
            "test" + File.separator + "resources" + File.separator + "config.properties";


    {
        try {
            propsFile = new FileInputStream(System.getProperty("user.dir") + configPropsPath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
