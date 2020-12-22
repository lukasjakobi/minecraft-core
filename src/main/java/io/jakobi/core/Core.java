package io.jakobi.core;

import io.jakobi.core.command.CorePlayerCommand;
import io.jakobi.core.command.PlayerCommandPreProcessListener;
import io.jakobi.core.log.Logger;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.reflect.ClassPath;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

/**
 * Core Main Class
 *
 * @author Lukas Jakobi <lukas@jakobi.io>
 * @since 11.12.2020
 * @copyright https://github.com/lukasjakobi/minecraft-core/blob/master/LICENSE
 */
public class Core extends JavaPlugin {

    private static Core instance;

    private String errorMessage = "§cYou don't have permission to execute this command";

    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;

        // events
        this.registerListener(this.getClass(), "io.jakobi.core.listener");
        this.getServer().getPluginManager().registerEvents(new PlayerCommandPreProcessListener(), this);

        Logger.success("Successfully initialized 'minecraft-core'");
    }

    public static Core getInstance() {
        return instance;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @SuppressWarnings("all")
    public void registerListener(Class mainClass, String packageName) {
        try {
            UnmodifiableIterator var2 = ClassPath.from(mainClass.getClassLoader()).getTopLevelClasses(packageName).iterator();

            while(var2.hasNext()) {
                ClassPath.ClassInfo classInfo = (ClassPath.ClassInfo)var2.next();
                Class c = Class.forName(classInfo.getName());
                if (Listener.class.isAssignableFrom(c)) {
                    Bukkit.getPluginManager().registerEvents((Listener)c.newInstance(), instance);
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException var5) {
            var5.printStackTrace();
        }
    }

    public void registerCommand(CorePlayerCommand command) {
        PlayerCommandPreProcessListener.commands.add(command);
    }
}
