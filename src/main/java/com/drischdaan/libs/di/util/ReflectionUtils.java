package com.drischdaan.libs.di.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Drischdaan on 22.01.2021
 * DependencyInjection © 2020
 */

public class ReflectionUtils {

    public static <T> T newInstance(Constructor<T> constructor, Object[] arguments) {
        try {
            return constructor.newInstance(arguments);
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public static void setFieldValue(Field field, Object value, Object instance) {
        try {
            boolean isAccessible = field.canAccess(instance);
            field.setAccessible(true);
            field.set(instance, value);
            field.setAccessible(isAccessible);
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static Object getFieldValue(Field field, Object instance) {
        try {
            return field.get(instance);
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public static boolean hasAnnotation(Field field, Class<? extends Annotation>  annotation) {
        return field.getAnnotation(annotation) != null;
    }

    public static boolean hasAnnotation(Method method, Class<? extends Annotation>  annotation) {
        return method.getAnnotation(annotation) != null;
    }

    public static boolean hasAnnotation(Constructor<?> constructor, Class<? extends Annotation>  annotation) {
        return constructor.getAnnotation(annotation) != null;
    }

    public static boolean hasAnnotation(Class<?> clazz, Class<? extends Annotation>  annotation) {
        return clazz.getAnnotation(annotation) != null;
    }

    public static Annotation getAnnotation(Field field, Class<? extends Annotation>  annotation) {
        return field.getAnnotation(annotation);
    }

    public static Annotation getAnnotation(Method method, Class<? extends Annotation>  annotation) {
        return method.getAnnotation(annotation);
    }

    public static Annotation getAnnotation(Constructor<?> constructor, Class<? extends Annotation>  annotation) {
        return constructor.getAnnotation(annotation);
    }

    public static Annotation getAnnotation(Class<?> clazz, Class<? extends Annotation>  annotation) {
        return clazz.getAnnotation(annotation);
    }

    public static Constructor<?> getConstructorWithAnnotation(Class<?> clazz, Class<? extends Annotation> annotation) {
        Constructor<?> constructor = null;
        Optional<Constructor<?>> optionalConstructor = Arrays.stream(clazz.getConstructors()).filter(con -> ReflectionUtils.getAnnotation(con, annotation) != null).findFirst();
        if(optionalConstructor.isPresent())
            constructor = optionalConstructor.get();
        return constructor;
    }

    public static Constructor<?> getDefaultConstructor(Class<?> clazz) {
        return clazz.getDeclaredConstructors().length != 0 ? clazz.getDeclaredConstructors()[0] : null;
    }

    public static Field[] getFieldsWithAnnotation(Class<?> clazz, Class<? extends Annotation> annotation) {
        List<Field> fields = new ArrayList<>();
        Arrays.stream(clazz.getDeclaredFields()).forEach(field -> {
            if(ReflectionUtils.hasAnnotation(field, annotation))
                fields.add(field);
        });
        return fields.toArray(new Field[0]);
    }

}
