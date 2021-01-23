package com.drischdaan.libs.di.provider;

import com.drischdaan.libs.di.api.annotations.INullInject;
import com.drischdaan.libs.di.api.annotations.Inject;
import com.drischdaan.libs.di.api.provider.IProvider;
import com.drischdaan.libs.di.api.provider.IProviderResolver;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Drischdaan on 22.01.2021
 * DependencyInjection © 2020
 */

public class ProviderResolver implements IProviderResolver {

    private HashMap<String, IProvider<?>> providers;

    public ProviderResolver(HashMap<String, IProvider<?>> providers) {
        this.providers = providers;
    }

    @Override
    public <T> T resolveProvider(String token) {
        if(!this.providers.containsKey(token)) {
            System.err.println("Unable to find provider for '" + token + "'");
            return null;
        }
        IProvider<?> provider = this.providers.get(token);
        return (T) provider.getLifetime().resolve(provider, this);
    }

    @Override
    public Object[] resolveArguments(Constructor<?> constructor) {
        List<Object> arguments = new ArrayList<>();
        AtomicInteger index = new AtomicInteger(0);
        Arrays.stream(constructor.getParameterTypes()).forEach(parameter -> {
            Annotation[] annotations = constructor.getParameterAnnotations()[index.get()];
            Optional<Annotation> optionalAnnotation = Arrays.stream(annotations).filter(annotation -> annotation.annotationType() == Inject.class).findFirst();
            if(optionalAnnotation.isPresent()) {
                Inject inject = (Inject) optionalAnnotation.get();
                if(inject.value().getName().equals(INullInject.class.getName()))
                    arguments.add(this.resolveProvider(parameter.getName()));
                else
                    arguments.add(this.resolveProvider(inject.value().getName()));
            } else {
                arguments.add(this.resolveProvider(parameter.getName()));
            }
            index.set(index.get() + 1);
        });
        return arguments.toArray();
    }

}