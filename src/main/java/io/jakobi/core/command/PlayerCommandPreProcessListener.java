package io.jakobi.core.command;

import io.jakobi.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerCommandPreProcessListener implements Listener {

    public static List<CorePlayerCommand> commands = new ArrayList<>();

    @EventHandler
    public void onPlayerCommandPreProcess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String command = event.getMessage().replaceAll("/", "");

        String[] splitted = command.split(" ");
        String[] args = Arrays.copyOfRange(splitted, 1, splitted.length);
        String label = splitted[0];

        for (CorePlayerCommand playerCommand : commands) {
            for (String registeredCommand : playerCommand.getCommands()) {
                if (registeredCommand.equalsIgnoreCase(splitted[0])) {
                    event.setCancelled(true);

                    if (!player.hasPermission(playerCommand.neededPermission())) {
                        player.sendMessage(Core.getInstance().getErrorMessage());
                        return;
                    }

                    playerCommand.onPlayerCommand(player, args, label);
                }
            }
        }
    }
}