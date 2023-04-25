package com.keyboardninja.components;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.awt.*;

import static java.lang.Thread.sleep;

@Component
public class MouseInterruptionCounter implements Runnable{
    volatile boolean cancel = false;

    private final KeyListener keyListener;

    public MouseInterruptionCounter(KeyListener keyListener){
        this.keyListener = keyListener;
    }

    @PostConstruct
    public void init(){
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (!cancel) {
            AppState appState = keyListener.getAppState();
            if (appState.lastPointerInfo().getLocation().getX() != MouseInfo.getPointerInfo().getLocation().getX()
                    || appState.lastPointerInfo().getLocation().getY() != MouseInfo.getPointerInfo().getLocation().getY()
            ) {
                keyListener.setAppState(new AppState(true));
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

    @PreDestroy
    public void cancel(){
        cancel = true;
    }
}
