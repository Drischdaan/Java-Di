package com.drischdaan.libs.di.api.provider;

import com.drischdaan.libs.di.api.lifetime.ILifetime;

/**
 * @author Drischdaan on 22.01.2021
 * DependencyInjection © 2020
 */

public interface IProvider<T> {
    String getToken();
    Class<?> getProviderClass();
    ILifetime getLifetime();

    void setProviderClass(Class<?> providerClass);
    void setLifetime(ILifetime lifetime);
}