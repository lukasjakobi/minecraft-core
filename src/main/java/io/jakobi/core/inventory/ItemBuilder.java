package io.jakobi.core.inventory;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.Potion;

import java.util.*;

/**
 * Item Helper
 *
 * @author Lukas Jakobi <lukas@jakobi.io>
 * @since 11.12.2020
 * @copyright https://github.com/lukasjakobi/minecraft-core/Licence.md
 */
public class ItemBuilder {

    private final Material material;
    private int amount;
    private String displayName;
    private List<ItemFlag> itemFlags = new ArrayList<>();
    private List<String> lores = new ArrayList<>();
    private final HashMap<Enchantment, Integer> enchantments = new HashMap<>();
    private Damageable damageable;
    private Potion potion;
    private Color color;

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

    public Color getColor() {
        return color;
    }

    public ItemBuilder setColor(Color color) {
        this.color = color;

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

    public Potion getPotion() {
        return potion;
    }

    public ItemBuilder setPotion(Potion potion) {
        this.potion = potion;

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
            ((Damageable)itemMeta).setDamage(this.damageable.getDamage());
        }

        if (this.potion != null && this.material == Material.POTION) {
            this.potion.apply(itemStack);
        }

        if (this.color != null && itemMeta instanceof LeatherArmorMeta) {
            ((LeatherArmorMeta)itemMeta).setColor(this.color);
        }

        if (this.itemFlags.size() > 0) {
            itemMeta.addItemFlags(this.itemFlags.toArray(new ItemFlag[0]));
        }

        if (this.enchantments.size() > 0) {
            for (Enchantment enchantment : this.enchantments.keySet()) {
                itemMeta.addEnchant(enchantment, this.enchantments.get(enchantment), true);
            }
        }

        if (this.lores.size() > 0) {
            itemMeta.setLore(this.lores);
        }

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
