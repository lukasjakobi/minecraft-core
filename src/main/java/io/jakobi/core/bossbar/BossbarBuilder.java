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
 * @copyright https://github.com/lukasjakobi/minecraft-core/blob/master/LICENSE
 */
public class BossbarBuilder {

    private String text;
    private BarColor barColor;
    private BarStyle barStyle;
    private final List<BarFlag> barFlags;
    private org.bukkit.boss.BossBar bossBar;
    private double progress;

    public BossbarBuilder(String text, BarColor barColor, BarStyle barStyle, BarFlag... barFlags) {
        this.text = text;
        this.barColor = barColor;
        this.barStyle = barStyle;
        this.barFlags = Arrays.asList(barFlags);
    }

    public void sendBossBar() {
        org.bukkit.boss.BossBar bossBar = Bukkit.createBossBar(text, barColor, barStyle);

        // flags
        barFlags.forEach(bossBar::addFlag);

        // players
        Bukkit.getOnlinePlayers().forEach(bossBar::addPlayer);

        // progress
        bossBar.setProgress(validateProgress(progress));

        this.bossBar = bossBar;
    }

    public String getText() {
        return text;
    }

    public BossbarBuilder setText(String text) {
        this.text = text;

        return this;
    }

    public BarColor getBarColor() {
        return barColor;
    }

    public BossbarBuilder setBarColor(BarColor barColor) {
        this.barColor = barColor;

        return this;
    }

    public BarStyle getBarStyle() {
        return barStyle;
    }

    public BossbarBuilder setBarStyle(BarStyle barStyle) {
        this.barStyle = barStyle;

        return this;
    }

    public List<BarFlag> getBarFlags() {
        return barFlags;
    }

    public org.bukkit.boss.BossBar getBossBar() {
        return bossBar;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    private double validateProgress(double progress) {
        if (progress < 0) {
            return 0;
        }

        if (progress > 1) {
            return 1;
        }

        return progress;
    }
}
