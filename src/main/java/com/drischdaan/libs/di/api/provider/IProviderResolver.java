package com.drischdaan.libs.di.api.provider;

import java.lang.reflect.Constructor;

/**
 * @author Drischdaan on 22.01.2021
 * DependencyInjection © 2020
 */

public interface IProviderResolver {
    <T> T resolveProvider(String token);
    Object[] resolveArguments(Constructor<?> constructor);
}