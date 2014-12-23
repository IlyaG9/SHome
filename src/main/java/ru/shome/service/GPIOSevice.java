/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.shome.service;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 19.12.2014
 *
 * @author ILYA GOLOVACHEV
 */
public class GPIOSevice implements Runnable {

    final GpioController gpio = GpioFactory.getInstance();

    final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "Nasos", PinState.LOW);

    @Override
    public void run() {

        try {
            while (true) {
                pin.high();
                Thread.sleep(1000);
                pin.low();
                Thread.sleep(2000);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(GPIOSevice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
