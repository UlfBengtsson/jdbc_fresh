package org.example;

import org.example.dao.CountryDao;
import org.example.dao.CountryDaoJDBC;

import java.io.*;
import java.nio.file.Paths;
import java.util.Properties;

public class App
{
    private static String connectionString;
    private static String userName;
    private static String password;

    public static void main(String[] args )
    {
        //saveToPropFile("localhost","user", "password");

        readPropFile();
        System.out.println(connectionString + "\n" + userName + "\n" + password);

        CountryDaoJDBC countryDao = new CountryDaoJDBC(connectionString,userName,password);

        countryDao.getContinent("Asia").forEach(System.out::println);
        System.out.println("------------------------");
        countryDao.getContinentFirstLast("Asia").forEach(System.out::println);
    }

    private static void saveToPropFile(String connection, String loginName, String loginPassword) {
        try (OutputStream output = new FileOutputStream(Paths.get("src","main","resources","config.properties").toString())) {

            Properties prop = new Properties();

            // set the properties value
            prop.setProperty("db.url", connection);
            prop.setProperty("db.user", loginName);
            prop.setProperty("db.password", loginPassword);

            // save properties to project root folder
            prop.store(output, null);

            System.out.println(prop);

        } catch (IOException io) {
            io.printStackTrace();
        }

    }

    private static void readPropFile() {
        Properties prop = null;
        try (InputStream input = new FileInputStream(Paths.get("src","main","resources","config.properties").toString())) {

            prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
            }
            else {
                //load a properties file from class path, inside static method
                prop.load(input);

                //get the property value and print it out
                connectionString = prop.getProperty("db.url");
                userName = prop.getProperty("db.user");
                password = prop.getProperty("db.password");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
