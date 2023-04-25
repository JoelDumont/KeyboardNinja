package com.keyboardninja.components;

import jakarta.annotation.PostConstruct;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

@Component
public class KeyListener implements NativeKeyListener {
    private AppState appState;

    private AtomicInteger counter = new AtomicInteger(0);

    public KeyListener(AppState appState){
        this.appState = appState;
    }
    @PostConstruct
    public void init(){
        try {
            GlobalScreen.registerNativeHook();
            Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
            logger.setLevel(Level.OFF);
        } catch (NativeHookException e) {
            System.exit(-1);
        }
        GlobalScreen.addNativeKeyListener(this);
    }
    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        if(appState.flag()){
            counter.getAndIncrement();
            appState = new AppState(false);
            try {
                sleep(1000);
            } catch (InterruptedException ex) {

            }
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

    }

    public AppState getAppState() {
        return appState;
    }

    public void setAppState(AppState appState) {
        this.appState = appState;
    }

    public int getCount() {
        return counter.get();
    }
}
