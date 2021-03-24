package io.jakobi.core.command;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface CoreCommand {

    void onExecuteCommand(CommandSender commandSender, String[] args, String label);
    List<String> getCommands();
    String neededPermission();
    String getDescription();
    boolean isPlayerOnly();
}