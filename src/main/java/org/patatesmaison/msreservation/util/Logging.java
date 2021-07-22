package org.patatesmaison.msreservation.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;

@Slf4j
public class Logging {
    /**
     * @param type type de message
     * @param message un message :)
     * @param stLevel la position dans la stacktrace. Si la methode est appelée directement c'est 1
     * @return une string contenant le nom du fichier, la ligne d'excecution, le nom de la methode, le type de log, et un message
     */
    public static String getLogMessage(String type, String message, int stLevel) {
        StringBuilder stringBuilder = new StringBuilder("");

        if(StringUtils.isBlank(type)) stringBuilder.append("[INFO]");
        else stringBuilder.append(String.format("[%s]", StringUtils.upperCase(type)));

        // java 9 StackWalker serait mieux apparement - mais on est en java 8
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();

        if(stackTrace != null && stackTrace.length >= (stLevel + 1)) {
            stringBuilder.append(String.format("[%s]", stackTrace[stLevel].getFileName()));
            stringBuilder.append(String.format("[L%s]", stackTrace[stLevel].getLineNumber()));
            stringBuilder.append(String.format("[%s]", stackTrace[stLevel].getMethodName()));
        }

        if(message != null) {
            stringBuilder.append(String.format(" ->> %s", message));
        }

        stringBuilder.append(" \n ");
        return stringBuilder.toString();
    }

    public static String getLogMessage(String type, String message) {
        return getLogMessage(type, message, 2);
    }

    public static String getExceptionMessage(Exception e) {
        return getLogMessage("error", e.toString(), 2);
    }

    public static void log(String type, String message, int stLevel) {
        if ("error".equalsIgnoreCase(type)) {
            log.error(getLogMessage(type, message, stLevel));
            return;
        }

        if ("warn".equalsIgnoreCase(type)) {
            log.warn(getLogMessage(type, message, stLevel));
            return;
        }

        if ("debug".equalsIgnoreCase(type)) {
            log.debug(getLogMessage(type, message, stLevel));
            return;
        }

        if ("trace".equalsIgnoreCase(type)) {
            log.trace(getLogMessage(type, message, stLevel));
            return;
        }

        log.info(getLogMessage("info", message, stLevel));
    }

    public static void logStackTrace(Exception e) {
        log.error(getLogMessage("error", "", 2), e);
    }

    public static void log(String type, String message) {
        log(type, message, 3);
    }

    public static void logEnter() {
        log("info", "enter", 3);
    }

//    public static void logEnterWithUserLogin(UserDTO userDTO) {
//        log("info", String.format("enter - login == %s", userDTO.getLogin()), 3);
//    }

    public static void logLeave() {
        log("info", "leave", 3);
    }

    public static String toStringNotNull(Object object) {
        if(object == null) return "";

        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(object, ToStringStyle.JSON_STYLE) {

            @Override
            protected boolean accept(Field field) {
                try {
                    return super.accept(field)
                            && field.get(object) != null
                            && !(field.get(object) instanceof SimpleDateFormat)
                            && !Modifier.isStatic(field.getModifiers());
                }
                catch (IllegalAccessException e) {
                    return super.accept(field);
                }
            }
        };

        return builder.toString();
    }
}
