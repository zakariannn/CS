package Homeworks;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ReflMethod {


    public static Object CopyObject(Object object) {
        try {
            Object clone = object.getClass().newInstance();

            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);

                if (field.get(object) == null || Modifier.isFinal(field.getModifiers())) {
                    continue;
                }

                if (field.getType().isPrimitive()
                        || field.getType().equals(String.class)
                        || field.getType().getSuperclass().equals(Number.class)
                        || field.getType().equals(Boolean.class)) {
                    field.set(clone, field.get(object));
                } else {
                    field.set(clone, CopyObject(field.get(object)));
                }
            }

            return clone;
        } catch (Exception e) {
            return null;
        }
    }
}
