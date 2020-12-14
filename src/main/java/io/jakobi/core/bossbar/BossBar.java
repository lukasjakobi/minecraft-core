package io.jakobi.core.bossbar;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;

import java.util.Arrays;
import java.util.List;

/**
 * BossBar Helper
 *
 * @author Lukas Jakobi <lukas@jakobi.io>
 * @since 11.12.2020
 * @copyright https://github.com/lukasjakobi/minecraft-core/Licence.md
 */
public class BossBar {

    private String text;
    private BarColor barColor;
    private BarStyle barStyle;
    private final List<BarFlag> barFlags;

    public BossBar(String text, BarColor barColor, BarStyle barStyle, BarFlag... barFlags) {
        this.text = text;
        this.barColor = barColor;
        this.barStyle = barStyle;
        this.barFlags = Arrays.asList(barFlags);
    }

    public String getText() {
        return text;
    }

    public BossBar setText(String text) {
        this.text = text;

        return this;
    }

    public BarColor getBarColor() {
        return barColor;
    }

    public BossBar setBarColor(BarColor barColor) {
        this.barColor = barColor;

        return this;
    }

    public BarStyle getBarStyle() {
        return barStyle;
    }

    public BossBar setBarStyle(BarStyle barStyle) {
        this.barStyle = barStyle;

        return this;
    }

    public List<BarFlag> getBarFlags() {
        return barFlags;
    }

    public void sendToPlayers() {
        Bukkit.createBossBar(this.text, this.barColor, this.barStyle);
    }
}
