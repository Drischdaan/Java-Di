package com.drischdaan.libs.di;

import com.drischdaan.libs.di.api.container.IContainer;
import com.drischdaan.libs.di.api.provider.IProvider;
import com.drischdaan.libs.di.api.provider.IProviderFactory;
import com.drischdaan.libs.di.provider.Provider;
import com.drischdaan.libs.di.provider.ProviderFactory;
import com.drischdaan.libs.di.provider.ProviderResolver;

import java.util.HashMap;

/**
 * @author Drischdaan on 22.01.2021
 * DependencyInjection © 2020
 */

public class Container implements IContainer {

    private HashMap<String, IProvider<?>> providers;

    public Container() {
        this.providers = new HashMap<>();
    }

    @Override
    public <T> IProviderFactory<T> bind(Class<T> token) {
        return this.bind(token.getName());
    }

    @Override
    public <T> IProviderFactory<T> bind(String token) {
        Provider<T> provider = new Provider<T>(token);
        this.providers.put(token, provider);
        return new ProviderFactory<T>(provider);
    }

    @Override
    public <T> T resolve(Class<T> token) {
        return this.resolve(token.getName());
    }

    @Override
    public <T> T resolve(String token) {
        return new ProviderResolver(this.providers).resolveProvider(token);
    }


}