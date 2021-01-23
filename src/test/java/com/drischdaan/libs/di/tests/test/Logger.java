package com.drischdaan.libs.di.tests.test;

/**
 * @author Drischdaan on 23.01.2021
 * DependencyInjection © 2020
 */

public class Logger implements ILogger {

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

}
