# Minecraft Core

A Minecraft Spigot Add-On to increase your speed of development. Creating Items, Inventorys, Scoreboards and more the easy way.

# Getting Started

## Maven

```
        <repository>
            <id>minecraft-core</id>
            <name>Minecraft Core</name>
            <url>https://cdn.jakobi.io/maven/minecraft-core/snapshots</url>
        </repository>
```

```
        <dependency>
            <groupId>io.jakobi</groupId>
            <artifactId>minecraft-core</artifactId>
            <version>1.0.0-SNAPSHOT</version>
            <scope>provided</scope>
        </dependency>
```

## Documentation

### ItemBuilder

A builder to create items with every kind of parameter

```
ItemBuilder itemBuilder = new ItemBuilder(Material.IRON_SWORD);

itemBuilder.setDisplayName("§eIron Sword"); // updates the item name
itemBuilder.setLores("§fItem Description"); // updates the item description
itemBuilder.setAmount(1); // updates the item amount
itemBuilder.setItemFlags(ItemFlag.HIDE_ATTRIBUTES); // updates the item flags
itemBuilder.setColor(Color.RED); // updates the color (if supported)

ItemStack itemStack = itemBuilder.toItemStack(); // build itemstack
```

OR SHORTER

```
ItemStack itemStack = new ItemBuilder(Material.IRON_SWORD).setDisplayName("§eIron Sword").setLores("§fItem Description").setItemFlags(ItemFlag.HIDE_ATTRIBUTES).setColor(Color.RED).toItemStack();
```

### InventoryBuilder
A helper to create inventorys. This can also use ItemBuilders.

```
InventoryBuilder inventoryBuilder = new InventoryBuilder("Custom Inventory", 9 * 3);

inventoryBuilder.withBackground(itemBuilder); // set all slots to a specific item
inventoryBuilder.addItem(0, itemBuilder); // add an item to the inventory
inventoryBuilder.disableInteraction(true); // cancel inventory click event

inventoryBuilder.openToPlayer(player); // open inventory to a player
```
