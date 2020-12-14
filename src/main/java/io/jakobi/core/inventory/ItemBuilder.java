package io.jakobi.core.inventory;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

/**
 * Item Helper
 *
 * @author Lukas Jakobi <lukas@jakobi.io>
 * @copyright https://github.com/lukasjakobi/minecraft-core/blob/master/LICENSE
 * @since 11.12.2020
 */
public class ItemBuilder {

    private final Material material;
    private int amount;
    private String displayName;
    private List<ItemFlag> itemFlags = new ArrayList<>();
    private List<String> lores = new ArrayList<>();
    private final HashMap<Enchantment, Integer> enchantments = new HashMap<>();
    private Damageable damageable;
    private boolean unbreakable;

    public ItemBuilder(Material material) {
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ItemBuilder setDisplayName(String displayName) {
        this.displayName = displayName;

        return this;
    }

    public int getAmount() {
        return amount;
    }

    public ItemBuilder setAmount(int amount) {
        this.amount = amount;

        return this;
    }

    public List<String> getLores() {
        return lores;
    }

    public ItemBuilder setLores(String... lores) {
        this.lores = Arrays.asList(lores);

        return this;
    }

    public Damageable getDamageable() {
        return damageable;
    }

    public ItemBuilder setDamageable(Damageable damageable) {
        this.damageable = damageable;

        return this;
    }

    public HashMap<Enchantment, Integer> getEnchantments() {
        return enchantments;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        this.enchantments.put(enchantment, level);

        return this;
    }

    public List<ItemFlag> getItemFlags() {
        return itemFlags;
    }

    public ItemBuilder setItemFlags(ItemFlag... itemFlags) {
        this.itemFlags = Arrays.asList(itemFlags);

        return this;
    }

    public ItemBuilder addItemFlag(ItemFlag itemFlag) {
        this.itemFlags.add(itemFlag);

        return this;
    }

    public boolean isUnbreakable() {
        return unbreakable;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;

        return this;
    }

    public ItemStack toItemStack() {
        ItemStack itemStack = new ItemStack(this.material, this.amount);

        if (this.material == Material.AIR) {
            return itemStack;
        }

        ItemMeta itemMeta = itemStack.getItemMeta();

        assert itemMeta != null;

        if (this.displayName != null) {
            itemMeta.setDisplayName(this.displayName);
        }

        if (this.damageable != null) {
            ((Damageable) itemMeta).setDamage(this.damageable.getDamage());
        }

        if (this.itemFlags.size() > 0) {
            itemMeta.addItemFlags(this.itemFlags.toArray(new ItemFlag[0]));
        }

        if (this.enchantments.size() > 0) {
            for (Enchantment enchantment : this.enchantments.keySet()) {
                itemMeta.addEnchant(enchantment, this.enchantments.get(enchantment), true);
            }
        }

        if (this.unbreakable) {
            itemMeta.spigot().setUnbreakable(true);
        }

        if (this.lores.size() > 0) {
            itemMeta.setLore(this.lores);
        }

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
