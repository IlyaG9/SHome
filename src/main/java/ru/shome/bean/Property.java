/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.shome.bean;

/**
 *
 * @author Hot
 */
public class Property {
    
    String programTitle;
    String arduinoURL;
    Integer runUpdateTime;
    Integer updateTime;
    Double liveRoomTemperature;
    Double holeTemperature;
    Double boilerTemperature;

    public Double getBoilerTemperature() {
        return boilerTemperature;
    }

    public void setBoilerTemperature(Double boilerTemperature) {
        this.boilerTemperature = boilerTemperature;
    }
    
    

    public Integer getRunUpdateTime() {
        return runUpdateTime;
    }

    public void setRunUpdateTime(Integer runUpdateTime) {
        this.runUpdateTime = runUpdateTime;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    
    public Double getHoleTemperature() {
        return holeTemperature;
    }

    public void setHoleTemperature(Double holeTemperature) {
        this.holeTemperature = holeTemperature;
    }
    
    public Double getLiveRoomTemperature() {
        return liveRoomTemperature;
    }

    public void setLiveRoomTemperature(Double liveRoomTemperature) {
        this.liveRoomTemperature = liveRoomTemperature;
    }
    
    public String getProgramTitle() {
        return programTitle;
    }

    public void setProgramTitle(String programTitle) {
        this.programTitle = programTitle;
    }

    
    public String getArduinoURL() {
        return arduinoURL;
    }

    public void setArduinoURL(String arduinoURL) {
        this.arduinoURL = arduinoURL;
    }
    
}
