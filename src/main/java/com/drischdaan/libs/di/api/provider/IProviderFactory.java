package com.drischdaan.libs.di.api.provider;

import com.drischdaan.libs.di.api.lifetime.ILifetime;

/**
 * @author Drischdaan on 22.01.2021
 * DependencyInjection © 2020
 */

public interface IProviderFactory<T> {
    <E extends T> IProviderFactory<T> to(Class<E> providerClass);
    IProviderFactory<T> lifetime(ILifetime lifetime);
}