package io.jakobi.core.log;

import org.bukkit.Bukkit;

public class Logger {

    public static void info(String message) {
        Bukkit.getConsoleSender().sendMessage(LogLevel.INFO.getPrefix() + message);
    }

    public static void err(String message) {
        Bukkit.getConsoleSender().sendMessage(LogLevel.ERROR.getPrefix() + message);
    }

    public static void success(String message) {
        Bukkit.getConsoleSender().sendMessage(LogLevel.SUCCESS.getPrefix() + message);
    }

    public static void warn(String message) {
        Bukkit.getConsoleSender().sendMessage(LogLevel.WARNING.getPrefix() + message);
    }
}
