package io.jakobi.core.command;

import org.bukkit.entity.Player;

import java.util.List;

public interface CorePlayerCommand {

    void onPlayerCommand(Player player, String[] args, String label);
    List<String> getCommands();
    String neededPermission();
    String getDescription();
}