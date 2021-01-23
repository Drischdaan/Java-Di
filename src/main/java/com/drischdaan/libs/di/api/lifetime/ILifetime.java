package com.drischdaan.libs.di.api.lifetime;

import com.drischdaan.libs.di.api.provider.IProvider;
import com.drischdaan.libs.di.api.provider.IProviderResolver;

/**
 * @author Drischdaan on 22.01.2021
 * DependencyInjection © 2020
 */

public interface ILifetime {
    <T> T resolve(IProvider<T> provider, IProviderResolver providerResolver);
}