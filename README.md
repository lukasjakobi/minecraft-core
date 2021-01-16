# Minecraft Core

A Minecraft Spigot add-on to increase your speed of development. Create items, inventorys, scoreboards and more the easy way.

## Credits
Special Thanks to Christian Tschörner, who both gave me this idea and contributed valuable code! Thanks a lot!

https://github.com/christiantschoerner (@christiantschoerner)


# Getting Started

## Using the JAR

1. Download the JAR here: https://cdn.jakobi.io/wp-content/2021/1/minecraft-core-latest.jar
2. Open your Projects Libraries (File -> Project Structure -> Libraries) and click the '+' to add the JAR to your Project Libraries
3. Start coding!

## Maven (Not Working)

```
        <repository>
            <id>minecraft-core</id>
            <name>Minecraft Core</name>
            <url>https://maven.jakobi.io/minecraft-core/snapshots</url>
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

### Events

You want to register a whole package worth of events at once? Then this is what you need:
```
Core.getInstance().registerListener(YOUR_MAIN.getClass(), "path.to.package");
```
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

A helper to create items

```
ItemBuilder itemBuilder = new ItemBuilder(Material.IRON_SWORD);

itemBuilder.setDisplayName("§eIron Sword"); // updates the item name
itemBuilder.setLores("§fItem Description"); // updates the item description
itemBuilder.setAmount(1); // updates the item amount
itemBuilder.setItemFlags(ItemFlag.HIDE_ATTRIBUTES); // add item flags
itemBuilder.addEnchantment(Enchantment.ARROW_FIRE, 1); // add enchantments
itemBuilder.setUnbreakable(true); // set item unbreakable

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
inventoryBuilder.setItem(0, itemBuilder); // add an item to the inventory
inventoryBuilder.disableInteraction(true); // cancel inventory click event

inventoryBuilder.openToPlayer(player); // open inventory to a player
```


### ActionbarBuilder
A helper to create Actionbars

```
ActionbarBuilder actionbarBuilder = new ActionbarBuilder("§fText");
actionbarBuilder.showTime(5); // time to show in seconds
actionbarBuilder.showPlayer(player); // send to player
```


### BossbarBuilder
A helper to create Actionbars

```
BossbarBuilder bossbarBuilder = new BossbarBuilder("§fText", BarColor.WHITE, BarStyle.SOLID, BarFlag.CREATE_FOG);
bossbarBuilder.setProgress(0.5); // progress from 0 to 1 (0.5 ==> 50%)
bossbarBuilder.sendBossBar(); // send to players
```


### ScoreboardBuilder
A helper to create Scoreboards (supports dynamic values)

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
