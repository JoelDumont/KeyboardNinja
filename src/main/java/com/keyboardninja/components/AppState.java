package com.keyboardninja.components;

import org.springframework.stereotype.Component;

import java.awt.*;

@Component()
public record AppState(boolean flag, PointerInfo lastPointerInfo) {
    public AppState(){
        this(false,MouseInfo.getPointerInfo());
    }

    public AppState(boolean flag) {
        this(flag,MouseInfo.getPointerInfo());
    }
}
