package ru.shome;

import java.awt.EventQueue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import ru.shome.bean.Property;
import ru.shome.service.GPIOSevice;
import ru.shome.service.SMSService;
import ru.shome.service.TemperatureService;
import ru.shome.view.MainView;

/**
 * 27.11.2014
 *
 * @author ILYA GOLOVACHEV
 */
public abstract class Main {

    public static Property pr = null;

    public static void main(String[] args) {
        initBeans2();
        startServices();
    }

    private static void startServices() {
        Thread updateTemperature = new Thread(new TemperatureService(pr.getArduinoURL()));
        updateTemperature.start();
        SMSService smsService=new SMSService();
        Thread runSMSReport=new Thread(smsService);
        runSMSReport.start();
        String osName = System.getProperty("os.name");
        System.out.println(osName);
        if (osName.equals("Linux")) {
            Thread updateGPIO = new Thread(new GPIOSevice());
            updateGPIO.start();     
            smsService.send("from:SH Linux", "System SH Started.");
        } else if (osName.contains("Windows")) {
            showMainForm();
        }
        
    }

    private static void initBeans2() {
        Property property = new Property();
        property.setArduinoURL("http://192.168.0.177");
       // property.setArduinoURL("http://192.168.0.9:8080/test.html");
        property.setProgramTitle("Smart Home System");
        property.setRunUpdateTime(2000);
        property.setUpdateTime(3000);
        property.setBoilerTemperature(Double.valueOf("0"));
        property.setTemperatureBoilerOff(Double.valueOf("20"));
        property.setTemperatureBoilerOn(Double.valueOf("25"));
        property.setGmailAccaunt("ilya.golovachev9@gmail.com");
        property.setGmailAccauntPassword("152909qw");
        property.setSmsruAccauntToSendSMS("e478b263-1a42-bde4-6d97-ffadbfcc10bd+79787905941@sms.ru");
        property.setHoursToSendReports(10);
     //   property.setIntervalToSendReports(86400000);
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
