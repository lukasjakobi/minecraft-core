package io.jakobi.core;

import com.google.common.collect.UnmodifiableIterator;
import com.google.common.reflect.ClassPath;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Core extends JavaPlugin {

    private static Core instance;

    @Override
    public void onEnable() {
        super.onEnable();

        instance = this;
        this.registerListener(this.getClass(), "io.jakobi.core.listener");
    }

    public static Core getInstance() {
        return instance;
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
}
