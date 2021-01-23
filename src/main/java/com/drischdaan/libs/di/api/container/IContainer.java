package com.drischdaan.libs.di.api.container;

import com.drischdaan.libs.di.api.provider.IProviderFactory;

/**
 * @author Drischdaan on 22.01.2021
 * DependencyInjection © 2020
 */

public interface IContainer {
    <T> IProviderFactory<T> bind(Class<T> token);
    <T> IProviderFactory<T> bind(String token);
    <T> T resolve(Class<T> token);
    <T> T resolve(String token);
}