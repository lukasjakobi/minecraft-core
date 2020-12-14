package io.jakobi.core.inventory;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.junit.Test;

public class ItemBuilderTest {

    private final Material material = Material.RED_WOOL;
    private final String displayName = "§fTest";
    private final String lore = "§fLore";
    private final int amount = 10;
    private final ItemFlag itemFlag = ItemFlag.HIDE_ATTRIBUTES;

    @Test
    public void testBuildItemMaterial() {
        ItemBuilder itemBuilder = new ItemBuilder(material);

        assert itemBuilder.getMaterial() == material;
    }

    @Test
    public void testBuildItemDisplayName() {
        ItemBuilder itemBuilder = new ItemBuilder(material).setDisplayName(displayName);

        assert itemBuilder.getDisplayName().equals(displayName);
    }

    @Test
    public void testBuildItemAmount() {
        ItemBuilder itemBuilder = new ItemBuilder(material).setAmount(amount);

        assert itemBuilder.getAmount() == amount;
    }

    @Test
    public void testBuildItemItemFlag() {
        ItemBuilder itemBuilder = new ItemBuilder(material).setItemFlags(itemFlag);

        assert itemBuilder.getItemFlags().contains(itemFlag);
    }

    @Test
    public void testBuildItemEnchantment() {
        ItemBuilder itemBuilder = new ItemBuilder(material).addEnchantment(Enchantment.ARROW_FIRE, 1);

        assert itemBuilder.getEnchantments().containsKey(Enchantment.ARROW_FIRE);
    }

    @Test
    public void testBuildItemLores() {
        ItemBuilder itemBuilder = new ItemBuilder(material).setLores(lore);

        assert itemBuilder.getLores().contains(lore);
    }

    @Test
    public void testBuildItemComplete() {
        ItemBuilder itemBuilder = new ItemBuilder(material);
        itemBuilder.setDisplayName(displayName);
        itemBuilder.setAmount(amount);
        itemBuilder.setItemFlags(itemFlag);
        itemBuilder.setLores(lore);
        itemBuilder.addEnchantment(Enchantment.ARROW_FIRE, 1);

        assert itemBuilder.getMaterial() == material;
        assert itemBuilder.getDisplayName().equals(displayName);
        assert itemBuilder.getAmount() == amount;
        assert itemBuilder.getItemFlags().contains(itemFlag);
        assert itemBuilder.getLores().contains(lore);
        assert itemBuilder.getEnchantments().containsKey(Enchantment.ARROW_FIRE);
    }
}
