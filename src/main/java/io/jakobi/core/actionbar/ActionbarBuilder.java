package io.jakobi.core.actionbar;

import io.jakobi.core.Core;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ActionbarBuilder {

    private String text;
    private Integer seconds;

    public ActionbarBuilder(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public ActionbarBuilder setText(String text) {
        this.text = text;

        return this;
    }

    public Integer getShowTime() {
        return seconds;
    }

    public ActionbarBuilder setShowTime(int seconds) {
        this.seconds = seconds;

        return this;
    }

    public void showPlayer(Player player) {
        if (this.seconds != null) {
            BukkitRunnable bukkitRunnable = new BukkitRunnable() {
                final int i = 0;

                @Override
                public void run() {
                    if (i >= seconds) {
                        sendActionbar(player, "§r");
                        this.cancel();
                        return;
                    }
                    sendActionbar(player, text);
                }
            };
            bukkitRunnable.runTaskTimerAsynchronously(Core.getInstance(), 0, 20);
        } else {
            sendActionbar(player, this.text);
        }

    }

    public void showAllPlayers() {
        if (this.seconds != null) {
            BukkitRunnable bukkitRunnable = new BukkitRunnable() {
                final int i = 0;

                @Override
                public void run() {
                    if (i >= seconds) {
                        Bukkit.getOnlinePlayers().forEach(player -> sendActionbar(player, "§a"));
                        this.cancel();
                        return;
                    }
                    Bukkit.getOnlinePlayers().forEach(player -> sendActionbar(player, text));
                }
            };
            bukkitRunnable.runTaskTimerAsynchronously(Core.getInstance(), 0, 20);
        } else {
            Bukkit.getOnlinePlayers().forEach(player -> sendActionbar(player, text));
        }
    }

    private void sendActionbar(Player player, String text) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(text));
    }
}