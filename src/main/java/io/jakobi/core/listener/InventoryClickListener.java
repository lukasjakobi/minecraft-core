package io.jakobi.core.listener;

import io.jakobi.core.inventory.InventoryBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * InventoryClickListener Class
 *
 * @author Lukas Jakobi <lukas@jakobi.io>
 * @since 14.12.2020
 * @copyright https://github.com/lukasjakobi/minecraft-core/blob/master/LICENSE
 */
public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (InventoryBuilder.inventories.containsKey(player)) {
            InventoryBuilder inventoryBuilder = InventoryBuilder.inventories.get(player);

            if (inventoryBuilder.interactionDisabled()) {
                event.setCancelled(true);
            }
        }
    }
}
