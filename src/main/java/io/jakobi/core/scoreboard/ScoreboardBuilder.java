package io.jakobi.core.scoreboard;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Scoreboard Helper
 *
 * @author Lukas Jakobi <lukas@jakobi.io>
 * @since 11.12.2020
 * @copyright https://github.com/lukasjakobi/minecraft-core/Licence.md
 */
public class ScoreboardBuilder {

    private String title;
    private final List<String> lines;

    /**
     * Create new Scoreboard
     * @param title Scoreboard title
     */
    public ScoreboardBuilder(String title) {
        this.title = title;
        this.lines = new ArrayList<>();
    }

    /**
     * Updates the title of the scoreboard. Changes will not be updated automatically
     *
     * @param title Scoreboard title
     * @return Scoreboard Instance
     */
    public ScoreboardBuilder setTitle(String title) {
        this.title = title;

        return this;
    }

    /**
     * Adds a clear line to the scoreboard
     *
     * @return Scoreboard Instance
     */
    public ScoreboardBuilder addEmptyLine() {
        this.lines.add("§r");

        return this;
    }

    /**
     * Adds a static line to the scoreboard. If you want the line to be updatable, use addUpdateLine()
     *
     * @return Scoreboard Instance
     */
    public ScoreboardBuilder addLine(String line) {
        this.lines.add(line);

        return this;
    }

    /**
     * Adds a updating line to the scoreboard. Use %s to represent updating values
     *
     * @return Scoreboard Instance
     */
    public ScoreboardBuilder addUpdateLine(String key, String prefix, String suffix) {
        //this.lines.add(line);

        return this;
    }

    /**
     * Send the scoreboard to a specific player
     *
     * @param player The Player
     */
    public void showPlayer(Player player) {

    }


    /**
     * Send the scoreboard to all players
     */
    public void showAllPlayers() {

    }
}
