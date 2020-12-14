package io.jakobi.core.inventory;

import io.jakobi.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * BossBar Helper
 *
 * @author Lukas Jakobi <lukas@jakobi.io>
 * @since 14.12.2020
 * @copyright https://github.com/lukasjakobi/minecraft-core/blob/master/LICENSE
 */
public class InventoryBuilder {

    public static HashMap<Player, InventoryBuilder> inventories = new HashMap<>();
    public static ArrayList<Player> closeBypass = new ArrayList<>();
    private final HashMap<Integer, ItemBuilder> items = new HashMap<>();
    private Runnable closeRunnable;
    private final Inventory inventory;
    private String title;
    private boolean disable;

    public InventoryBuilder(String title, int size) {
        this.title = title;
        this.inventory = Bukkit.createInventory(null, size, title);
    }

    public InventoryBuilder withBackground(ItemBuilder itemBuilder) {
        for(int i = 0; i < this.inventory.getSize(); ++i) {
            this.inventory.setItem(i, itemBuilder.toItemStack());
        }

        return this;
    }

    public InventoryBuilder disableInteraction(boolean disable) {
        this.disable = disable;
        return this;
    }

    public boolean interactionDisabled() {
        return this.disable;
    }

    public InventoryBuilder addItem(int position, ItemBuilder itemBuilder) {
        ItemStack itemStack = itemBuilder.toItemStack();
        this.items.put(position, itemBuilder);
        this.inventory.setItem(position, itemStack);

        return this;
    }

    public InventoryBuilder addItem(int position, ItemStack itemStack) {
        ItemBuilder itemBuilder = new ItemBuilder(itemStack.getType());
        this.items.put(position, itemBuilder);
        this.inventory.setItem(position, itemStack);

        return this;
    }

    public InventoryBuilder onClose(Runnable closeRunnable) {
        this.closeRunnable = closeRunnable;

        return this;
    }

    public HashMap<Integer, ItemBuilder> getItems() {
        return this.items;
    }

    public String getTitle() {
        return title;
    }

    public InventoryBuilder setTitle(String title) {
        this.title = title;

        return this;
    }

    public void openToPlayer(Player player) {
        Bukkit.getScheduler().runTask(Core.getInstance(), () -> {
            if (inventories.containsKey(player)) {
                closeBypass.add(player);
            }

            inventories.put(player, this);
            player.openInventory(this.inventory);
        });
    }

    public Integer getSize() {
        return this.inventory.getSize();
    }

    public String getName() {
        return this.title;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public Runnable getCloseRunnable() {
        return this.closeRunnable;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException var2) {
            var2.printStackTrace();
            return null;
        }
    }
}
