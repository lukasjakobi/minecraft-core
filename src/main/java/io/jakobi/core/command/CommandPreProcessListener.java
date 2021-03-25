package io.jakobi.core.command;

import io.jakobi.core.Core;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandPreProcessListener implements Listener {

    public static List<CoreCommand> commands = new ArrayList<>();

    @EventHandler
    public void onPlayerCommandPreProcess(PlayerCommandPreprocessEvent event) {
        this.executeCommand(event.getPlayer(), event.getMessage());
    }

    @EventHandler
    public void onServerCommandPreProcess(ServerCommandEvent event) {
        this.executeCommand(event.getSender(), event.getCommand());
    }

    private void executeCommand(CommandSender commandSender, String command) {
        command = command.replaceAll("/", "");

        String[] splitted = command.split(" ");
        String[] args = Arrays.copyOfRange(splitted, 1, splitted.length);
        String label = splitted[0];

        for (CoreCommand coreCommand : commands) {
            for (String registeredCommand : coreCommand.getCommands()) {
                if (registeredCommand.equalsIgnoreCase(splitted[0])) {
                    // player-only command check
                    if (coreCommand.isPlayerOnly() && !(commandSender instanceof Player)) {
                        return;
                    }

                    // check if player has permission to execute command
                    if (commandSender instanceof Player && !commandSender.hasPermission(coreCommand.neededPermission())) {
                        commandSender.sendMessage(Core.getInstance().getErrorMessage());
                        return;
                    }

                    coreCommand.onExecuteCommand(commandSender, args, label);
                }
            }
        }
    }
}