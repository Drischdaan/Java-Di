package com.drischdaan.libs.di.provider;

import com.drischdaan.libs.di.api.lifetime.ILifetime;
import com.drischdaan.libs.di.api.provider.IProvider;

/**
 * @author Drischdaan on 22.01.2021
 * DependencyInjection © 2020
 */

public class Provider<T> implements IProvider<T> {

    private final String token;
    private Class<?> providerClass;
    private ILifetime lifetime;

    public Provider(String token) {
        this.token = token;
    }

    @Override
    public String getToken() {
        return this.token;
    }

    @Override
    public Class<?> getProviderClass() {
        return this.providerClass;
    }

    @Override
    public ILifetime getLifetime() {
        return this.lifetime;
    }

    @Override
    public void setProviderClass(Class<?> providerClass) {
        this.providerClass = providerClass;
    }

    @Override
    public void setLifetime(ILifetime lifetime) {
        this.lifetime = lifetime;
    }

}