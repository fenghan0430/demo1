package gui;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Properties;

public class ProDemo {
    public static void main(String[] args) throws Exception{
        Properties pro = new Properties();
        BufferedReader reader=new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("hobby.properties")));

        pro.load(reader);
        for(String key:pro.stringPropertyNames()){
            System.out.println(key+"="+pro.getProperty(key));
        }
    }
}
