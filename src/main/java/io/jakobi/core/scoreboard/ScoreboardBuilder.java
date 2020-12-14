package io.jakobi.core.scoreboard;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.Random;

/**
 * Scoreboard Helper
 *
 * @author Lukas Jakobi <lukas@jakobi.io>
 * @copyright https://github.com/lukasjakobi/minecraft-core/blob/master/LICENSE
 * @since 11.12.2020
 */
public class ScoreboardBuilder {

    public static HashMap<Player, ScoreboardBuilder> scoreboards = new HashMap<>();
    public static boolean disableAutomaticJoinScoreboard = false;

    private final Scoreboard scoreboard;
    private final Player player;
    private final HashMap<String, String> updatePrefixes = new HashMap<>();
    private final HashMap<String, String> updateSuffixes = new HashMap<>();

    private Objective objective;
    private String title;
    private int score = 16;

    public ScoreboardBuilder(Player player, String title) {
        this.player = player;
        this.title = title;
        this.scoreboard = player.getScoreboard();
        this.objective = scoreboard.getObjective("aaa");

        if (this.objective == null) {
            objective = scoreboard.registerNewObjective("aaa", "bbb");
        }

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(title);
    }

    public ScoreboardBuilder disabledSideboard() {
        if (this.objective != null) {
            this.objective.unregister();
        }

        return this;
    }

    public ScoreboardBuilder resetScores() {
        for (Objective objective : scoreboard.getObjectives()) {
            objective.unregister();
        }
        objective = scoreboard.registerNewObjective("aaa", "bbb");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(this.title);

        return this;
    }

    public ScoreboardBuilder addEmptyLine() {
        objective.getScore(getRandomEntry(8)).setScore(this.score);
        this.score--;

        return this;
    }

    public ScoreboardBuilder addLine(String line) {
        objective.getScore(line).setScore(this.score);
        this.score--;

        return this;
    }

    public ScoreboardBuilder addUpdateRow(String key, String prefix, String suffix) {
        String entry = getRandomEntry(3);
        Team team = scoreboard.getTeam(key);

        if (team == null) {
            team = scoreboard.registerNewTeam(key);
        }

        updatePrefixes.put(key, prefix);
        updateSuffixes.put(key, suffix);

        team.setPrefix(prefix);
        team.setSuffix(suffix);
        team.addEntry(entry);
        addLine(entry);

        return this;
    }

    public ScoreboardBuilder updateRow(String key, String prefixValue, String suffixValue) {
        Team team = scoreboard.getTeam(key);

        if (team != null) {
            String prefix = updatePrefixes.get(key);
            String suffix = updateSuffixes.get(key);

            if (prefix != null) {
                team.setPrefix(String.format(prefix, prefixValue));
            }

            if (suffix != null) {
                team.setSuffix(String.format(suffix, suffixValue));
            }
        }

        return this;
    }

    public ScoreboardBuilder updateTitle(String string) {
        this.title = string;
        this.objective.setDisplayName(string);

        return this;
    }

    private String getRandomEntry(int length) {
        StringBuilder entry = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int random = new Random().nextInt(9);
            entry.append("§").append(random);
        }

        return entry.toString();
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public void sendPlayer() {
        player.setScoreboard(scoreboard);
        ScoreboardBuilder.scoreboards.put(player, this);
    }

    public static ScoreboardBuilder getBuilder(Player player) {
        return ScoreboardBuilder.scoreboards.get(player);
    }
}
