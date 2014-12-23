/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.shome.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ru.shome.Main;

/**
 * 27.11.2014
 *
 * @author ILYA GOLOVACHEV
 */
public class TemperatureService implements Runnable {

    private String url;
    private Map<String, Double> indicators = new HashMap<>();

    public TemperatureService(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        updateDataInProperty();
    }

    private void getSensorsValue() {
        try {
            Document document = Jsoup.connect(url).get();
            //T1
            Element t1 = document.select("t1").first();
            Double t1Value = Double.valueOf(t1.text());
            indicators.put("t1", t1Value);
            //T2
            Element t2 = document.select("t2").first();
            Double t2Value = Double.valueOf(t2.text());
            indicators.put("t2", t2Value);
            //T3
            Element t3 = document.select("t3").first();
            Double t3Value = Double.valueOf(t3.text());
            indicators.put("t3", t3Value);

        } catch (IOException ex ) {
            System.out.println("connect timed out");
          //  Logger.getLogger(TemperatureService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateDataInProperty() {
        Timer timer = new Timer();
        TimerTask ttask = new TimerTask() {

            @Override
            public void run() {
                getSensorsValue();
                Double boilerTemperatur=indicators.get("t1");
                Main.pr.setBoilerTemperature(boilerTemperatur);
                Double liveroomTemperature = indicators.get("t2");
                Main.pr.setLiveRoomTemperature(liveroomTemperature);
                Double holeTemperature=indicators.get("t3");
                Main.pr.setHoleTemperature(holeTemperature);
                System.out.println("Update temperature in service");
            }
        };
        timer.scheduleAtFixedRate(ttask, Main.pr.getRunUpdateTime(), Main.pr.getUpdateTime());
    }

}
