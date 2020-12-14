package io.jakobi.core.log;

import net.md_5.bungee.api.ChatColor;

public enum LogLevel {

    ERROR("[" + ChatColor.RED + "Core" + ChatColor.RESET + "] "),
    WARNING("[" + ChatColor.YELLOW + "Core" + ChatColor.RESET + "] "),
    SUCCESS("[" + ChatColor.DARK_GREEN + "Core" + ChatColor.RESET + "] "),
    INFO("[" + ChatColor.LIGHT_PURPLE + "Core" + ChatColor.RESET + "] ");

    private final String prefix;

    LogLevel(String prefix){
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}