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
    Double temperatureBoilerOn;
    Double temperatureBoilerOff;

    public Double getTemperatureBoilerOn() {
        return temperatureBoilerOn;
    }

    public void setTemperatureBoilerOn(Double temperatureBoilerOn) {
        this.temperatureBoilerOn = temperatureBoilerOn;
    }

    public Double getTemperatureBoilerOff() {
        return temperatureBoilerOff;
    }

    public void setTemperatureBoilerOff(Double temperatureBoilerOff) {
        this.temperatureBoilerOff = temperatureBoilerOff;
    }
    
    

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
