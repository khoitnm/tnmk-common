package org.tnmk.tech_common;

import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.time.temporal.Temporal;
import java.util.Date;

public class ReflectionUtils {


    public static void setPropertyWithConvertedValueIfPossible(String propertyName, Object targetObject, Object setValue) throws InvocationTargetException, IllegalAccessException {
        PropertyDescriptor propertyDescriptor = BeanUtils.getPropertyDescriptor(targetObject.getClass(), propertyName);
        Class propertyType = propertyDescriptor.getPropertyType();
        Object convertedValue = ValueConversionUtils.convertSimpleValue(setValue, propertyType);
        propertyDescriptor.getWriteMethod().invoke(targetObject, convertedValue);
    }

    public static void setProperty(String propertyName, Object targetObject, Object setValue) throws InvocationTargetException, IllegalAccessException {
        PropertyDescriptor propertyDescriptor = BeanUtils.getPropertyDescriptor(targetObject.getClass(), propertyName);
        propertyDescriptor.getWriteMethod().invoke(targetObject, setValue);
    }

    public static Object getProperty(Object targetObject, String propertyName) throws IllegalAccessException, InvocationTargetException {
        PropertyDescriptor propertyDescriptor = BeanUtils.getPropertyDescriptor(targetObject.getClass(), propertyName);
        if (propertyDescriptor == null) {
            String msg = String.format("Not found property `%s` in class `%s`", propertyName, targetObject.getClass());
            throw new IllegalAccessException(msg);
        }
        Object value = propertyDescriptor.getReadMethod().invoke(targetObject);
        return value;
    }

    /**
     * This method doesn't check Map, Array or Collection types
     *
     * @param type
     * @return
     */
    public static boolean isSimpleType(Class<?> type) {
        boolean result = BeanUtils.isSimpleValueType(type) || isDateTimeType(type);
        return result;
    }

    public static boolean isDateTimeType(Class<?> type) {
        return Temporal.class.isAssignableFrom(type) || Date.class.isAssignableFrom(type);
    }

}
