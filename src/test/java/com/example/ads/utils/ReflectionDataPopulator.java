package com.example.ads.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Random;

public class ReflectionDataPopulator {

    public static <T> T populate(Class<T> clazz) {
        try {
            T instance = clazz.getDeclaredConstructor().newInstance();
            for (Field field : clazz.getDeclaredFields()) {
                if (!Modifier.isFinal(field.getModifiers())) {
                    field.setAccessible(true);
                    Object value = generateDataForField(field);
                    field.set(instance, value);
                }
            }
            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Error populating data", e);
        }
    }

    private static Object generateDataForField(Field field) {
        Class<?> type = field.getType();
        if (type.isPrimitive() || String.class.equals(type)) {
            // 对于基础类型和字符串，返回一个示例值
            return getSampleData(type);
        } else {
            // 对于复杂类型，递归填充
            return populate(type);
        }
    }

    private static Object getSampleData(Class<?> type) {
        if (int.class.equals(type) || Integer.class.equals(type)) {
            return new Random().nextInt(100); // 示例值
        } else if (String.class.equals(type)) {
            return "ExampleString"; // 示例字符串
        }
        // 添加其他类型的处理
        return null;
    }
    private static Boolean isWrappedType(Class<?> type) {

        return null;
    }
}