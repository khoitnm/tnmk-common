package org.tnmk.tech_common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ValueConversionUtils {
    public static <T> T convertSimpleValue(Object value, Class<T> targetType) throws UnsupportedOperationException {
        if (value == null) {
            return null;
        }
        if (targetType.isAssignableFrom(String.class)) {
            return (T) String.valueOf(value);
        }
        if (targetType.isAssignableFrom(Boolean.class) || targetType == boolean.class) {
            return (T) Boolean.valueOf(String.valueOf(value));
        }
        if (targetType.isAssignableFrom(Long.class) || targetType == long.class) {
            return (T) NumberUtils.toLongIfPossible(value);
        }
        if (targetType.isAssignableFrom(Integer.class) || targetType == int.class) {
            return (T) NumberUtils.toIntegerIfPossible(value);
        }
        if (targetType.isAssignableFrom(Double.class) || targetType == double.class) {
            return (T) NumberUtils.toDoubleIfPossible(value);
        }
        if (targetType.isAssignableFrom(Float.class) || targetType == float.class) {
            return (T) NumberUtils.toFloatIfPossible(value);
        }
        if (targetType.isAssignableFrom(Short.class) || targetType == short.class) {
            return (T) NumberUtils.toShortIfPossible(value);
        }
        if (targetType.isAssignableFrom(LocalTime.class)) {
            return (T) LocalTime.parse(String.valueOf(value));
        }
        if (targetType.isAssignableFrom(LocalDate.class)) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/M/yyyy");
            String valueString = String.valueOf(value);
            if (StringUtils.isBlank(valueString)) {
                return null;
            }
            if (valueString.contains(".")) {
                valueString = valueString.replaceAll("\\.", "/");
            }
            LocalDate localDate = LocalDate.parse(valueString, dtf);
            return (T) localDate;
        }
        if (targetType.isAssignableFrom(LocalDateTime.class)) {
            return (T) LocalDateTime.parse(String.valueOf(value));
        }
        if (targetType.isAssignableFrom(ZonedDateTime.class)) {
            return (T) ZonedDateTime.parse(String.valueOf(value));
        }
        if (targetType.isAssignableFrom(OffsetDateTime.class)) {
            return (T) OffsetDateTime.parse(String.valueOf(value));
        }
        if (targetType.isAssignableFrom(Instant.class)) {
            return (T) Instant.parse(String.valueOf(value));
        }
        if (targetType.isAssignableFrom(Date.class)) {
            DateFormat dateFormat = new SimpleDateFormat();
            try {
                return (T) dateFormat.parse(String.valueOf(value));
            } catch (ParseException e) {
                String msg = String.format("Cannot convert value `%s` (type %s) to type %s",
                    value,
                    value.getClass(),
                    targetType);
                throw new UnsupportedOperationException(msg);
            }
        }

        String msg = String.format("Cannot convert value `%s` (type %s) to type %s",
            value,
            value.getClass(),
            targetType);
        throw new UnsupportedOperationException(msg);
    }
}
