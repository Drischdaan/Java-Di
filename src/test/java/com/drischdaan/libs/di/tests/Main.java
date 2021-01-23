package com.drischdaan.libs.di.tests;

import com.drischdaan.libs.di.Container;
import com.drischdaan.libs.di.api.container.IContainer;
import com.drischdaan.libs.di.lifetime.TransientLifetime;
import com.drischdaan.libs.di.tests.test.ILogger;
import com.drischdaan.libs.di.tests.test.Logger;
import com.drischdaan.libs.di.tests.test.Test;

/**
 * @author Drischdaan on 23.01.2021
 * DependencyInjection © 2020
 */

public class Main {

    public static void main(String[] args) {
        IContainer container = new Container();
        container.bind(ILogger.class).to(Logger.class).lifetime(new TransientLifetime());
        container.bind(Test.class).to(Test.class).lifetime(new TransientLifetime());

        Test test = container.resolve(Test.class);
        test.printHelloWorld();
    }

}