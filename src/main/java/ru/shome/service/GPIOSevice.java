package ru.shome.service;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.util.Timer;
import java.util.TimerTask;
import ru.shome.Main;

/**
 * 19.12.2014
 *
 * @author ILYA GOLOVACHEV
 */
public class GPIOSevice implements Runnable {

    final GpioController gpio = GpioFactory.getInstance();

    final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "Pump", PinState.LOW);

    @Override
    public void run() {
        Timer timer = new Timer();
        TimerTask ttask = new TimerTask() {

            @Override
            public void run() {
                if (Main.pr.getBoilerTemperature() > Main.pr.getTemperatureBoilerOn()) {
                    pin.high();
                    System.out.println("Pump on");
                }
                if (Main.pr.getBoilerTemperature() < Main.pr.getTemperatureBoilerOff()) {
                    pin.low();
                    System.out.println("Pump off");
                }
            }
        };
        timer.scheduleAtFixedRate(ttask, Main.pr.getRunUpdateTime(), Main.pr.getUpdateTime());
    }

}
