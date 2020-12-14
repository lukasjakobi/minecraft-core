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

### Commands

Creating custom commands was never easier. Set permissions and command aliases without registering them in the plugin.yml, fully automatic and lightning-fast

```
public class TestCommand implements CorePlayerCommand {

    @Override
    public void onPlayerCommand(Player player, String[] args, String label) {
        // command execution
    }

    @Override
    public List<String> getCommands() {
        return Arrays.asList("command", "command2", "command3");
    }

    @Override
    public String neededPermission() {
        return "permission.name";
    }

    @Override
    public String getDescription() {
        return "custom description";
    }
}
```

Registering the command

```
Core.getInstance().registerCommand(new TestCommand());
```

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


### ActionbarBuilder
Create Actionbars

```
ActionbarBuilder actionbarBuilder = new ActionbarBuilder("§fText");
actionbarBuilder.showTime(5); // time to show in seconds
actionbarBuilder.showPlayer(player); // send to player
```


### ScoreboardBuilder
Create Scoreboards (supports updating values)

```
ScoreboardBuilder scoreboardBuilder = new ScoreboardBuilder(player, "title");
scoreboardBuilder.addEmptyLine();
scoreboardBuilder.addLine("line");
scoreboardBuilder.addUpdateRow("coins", "§f%s ", "Coins");
```

Updating Values
```
ScoreboardBuilder.getBuilder(player).updateRow("coins", 2000, null);
ScoreboardBuilder.getBuilder(player).updateTitle("new title");
```
