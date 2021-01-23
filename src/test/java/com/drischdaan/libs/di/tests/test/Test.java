package com.drischdaan.libs.di.tests.test;

import com.drischdaan.libs.di.api.annotations.Inject;
import com.drischdaan.libs.di.api.annotations.InjectConstructor;

/**
 * @author Drischdaan on 23.01.2021
 * DependencyInjection © 2020
 */

public class Test {

    @Inject // Remove this when you use the inject constructor
    private ILogger logger;

    /** or use this:
     *
     * @InjectConstructor
     * public Test(ILogger logger) {
     *     this.logger = logger;
     * }
     *
     */

    public void printHelloWorld() {
        this.logger.print("Hello World");
    }

}
