package com.keyboardninja;

import com.keyboardninja.components.KeyListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

@SpringBootApplication(scanBasePackages = "com.keyboardninja")
public class KeyboardNinjaApplication {

    public static void main(String[] args) throws InterruptedException {

        SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(KeyboardNinjaApplication.class);
        springApplicationBuilder.headless(false);
        ConfigurableApplicationContext applicationContext = springApplicationBuilder.run(args);

        Scanner scanner = new Scanner(System.in);

        System.out.println("[q] = show number of switches and quit \n" +
                           "[l] = show number of switches and continue");

        while (scanner.next().equals("l")) {
            printCount(applicationContext);
        }

        printCount(applicationContext);
        System.exit(1);
    }

    private static void printCount(ConfigurableApplicationContext applicationContext) {
        KeyListener keyListener = (KeyListener) applicationContext.getBean("keyListener");
        System.out.println("Count: " + keyListener.getCount());
    }

}
