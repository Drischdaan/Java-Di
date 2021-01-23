package com.drischdaan.libs.di.provider;

import com.drischdaan.libs.di.api.lifetime.ILifetime;
import com.drischdaan.libs.di.api.provider.IProvider;
import com.drischdaan.libs.di.api.provider.IProviderFactory;

/**
 * @author Drischdaan on 22.01.2021
 * DependencyInjection © 2020
 */

public class ProviderFactory<T> implements IProviderFactory<T> {

    private final IProvider<T> provider;

    public ProviderFactory(IProvider<T> provider) {
        this.provider = provider;
    }

    @Override
    public <E extends T> IProviderFactory<T> to(Class<E> providerClass) {
        this.provider.setProviderClass(providerClass);
        return this;
    }

    @Override
    public IProviderFactory<T> lifetime(ILifetime lifetime) {
        this.provider.setLifetime(lifetime);
        return this;
    }

}