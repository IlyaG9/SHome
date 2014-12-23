/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.shome;

import java.awt.EventQueue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import ru.shome.bean.Property;
import ru.shome.service.GPIOSevice;
import ru.shome.service.TemperatureService;
import ru.shome.view.MainView;

/**
 * 27.11.2014
 *
 * @author ILYA GOLOVACHEV
 */
public class Main {

    public static Property pr = null;

    public static void main(String[] args) {
        initBeans2();
        startServices();
    }

    private static void startServices() {
        Thread updateTemperature = new Thread(new TemperatureService(pr.getArduinoURL()));
        updateTemperature.start();
        String osName = System.getProperty("os.name");
        System.out.println(osName);
        if (osName.equals("Linux")) {
            Thread updateGPIO = new Thread(new GPIOSevice());
            updateGPIO.start();
        } else if (osName.contains("Windows")) {
            showMainForm();
        }

    }

    private static void initBeans2() {
        Property property = new Property();
        property.setArduinoURL("http://192.168.0.177");
        //property.setArduinoURL("http://localhost:8080/test.html");
        property.setProgramTitle("Smart Home System");
        property.setRunUpdateTime(2000);
        property.setUpdateTime(3000);
        pr = property;
    }

    private static void initBeans() {
        ApplicationContext context = new FileSystemXmlApplicationContext("property.xml");
        Property property = (Property) context.getBean("property");
        pr = property;
    }

    private static void showMainForm() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainView(pr.getProgramTitle()).setVisible(true);
            }
        });
    }
}
