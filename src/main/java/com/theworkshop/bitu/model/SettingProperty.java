package com.theworkshop.bitu.model;

import java.io.*;
import java.util.Properties;

public class SettingProperty {

    private static String URL = "http://bitcore-node-1.mdev.brand--x.com:18332",
            USERNAME = "bitcoin",
            PASSWORD = "1bitcoin!";
    private static SettingProperty I = null;
    private static String DIR = System.getProperty("user.home") + File.separator + ".biturpc" + File.separator;
    private static String FILE_NAME = "biturpc.properties";
    private Properties properties = new Properties();

    private void initDir() {
        File file = new File(DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    private void readSetting() throws IOException {
        File file = new File(DIR + FILE_NAME);
        if (!file.exists()) {
            this.properties.setProperty("URL", URL);
            this.properties.setProperty("USERNAME", USERNAME);
            this.properties.setProperty("PASSWORD", PASSWORD);
            this.properties.store(new FileOutputStream(file), "SETTING");
        }else{
            this.properties.load(new FileInputStream(file));
        }
    }

    private void saveSetting(String url, String username, String password) throws IOException {
        File file = new File(DIR + FILE_NAME);
        this.properties.setProperty("URL", url);
        this.properties.setProperty("USERNAME", username);
        this.properties.setProperty("PASSWORD", password);
        this.properties.store(new FileOutputStream(file), "SETTING");
    }

    private static SettingProperty getInstance() {
        if (null == I) {
            I = new SettingProperty();
            I.initDir();
        }
        return I;
    }

    public static SettingProperty getSetting() {
        try {
            SettingProperty.getInstance().readSetting();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SettingProperty.getInstance();
    }

    public static String getUrl() {
        return SettingProperty.getSetting().properties.getProperty("URL");
    }

    public static String getUsername() {
        return SettingProperty.getSetting().properties.getProperty("USERNAME");
    }

    public static String getPassword() {
        return SettingProperty.getSetting().properties.getProperty("PASSWORD");
    }

    public static void storeSetting(String url, String username, String password) {
        try {
            SettingProperty.getInstance().saveSetting(url, username, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}