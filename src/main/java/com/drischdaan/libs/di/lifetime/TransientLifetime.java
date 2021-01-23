package com.drischdaan.libs.di.lifetime;

import com.drischdaan.libs.di.api.annotations.INullInject;
import com.drischdaan.libs.di.api.annotations.Inject;
import com.drischdaan.libs.di.api.annotations.InjectConstructor;
import com.drischdaan.libs.di.api.lifetime.ILifetime;
import com.drischdaan.libs.di.api.provider.IProvider;
import com.drischdaan.libs.di.api.provider.IProviderResolver;
import com.drischdaan.libs.di.util.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author Drischdaan on 22.01.2021
 * DependencyInjection © 2020
 */

public class TransientLifetime implements ILifetime {

    @Override
    public <T> T resolve(IProvider<T> provider, IProviderResolver providerResolver) {
        Constructor<?> constructor = ReflectionUtils.getConstructorWithAnnotation(provider.getProviderClass(), InjectConstructor.class);
        if(constructor == null) {
            constructor = ReflectionUtils.getDefaultConstructor(provider.getProviderClass());
            if(constructor == null)
                return null;
        }
        Object[] arguments = providerResolver.resolveArguments(constructor);
        T instance = (T) ReflectionUtils.newInstance(constructor, arguments);
        if(instance == null)
            return null;
        Field[] fields = ReflectionUtils.getFieldsWithAnnotation(provider.getProviderClass(), Inject.class);
        Arrays.stream(fields).forEach(field -> {
            Inject inject = (Inject) ReflectionUtils.getAnnotation(field, Inject.class);
            Class<?> providerToInject = inject.value();
            if(inject.value().getName().equals(INullInject.class.getName()))
                providerToInject = field.getType();
            Object providerInstance = providerResolver.resolveProvider(providerToInject.getName());
            if(providerInstance == null)
                return;
            ReflectionUtils.setFieldValue(field, providerInstance, instance);
        });
        return instance;
    }

}