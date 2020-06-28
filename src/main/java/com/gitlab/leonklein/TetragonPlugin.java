package com.gitlab.leonklein;

import com.gitlab.leonklein.configuration.Items;
import com.gitlab.leonklein.configuration.Locations;
import com.gitlab.leonklein.configuration.Messages;
import com.gitlab.leonklein.game.BlockGenerator;
import com.gitlab.leonklein.game.Game;
import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class TetragonPlugin extends JavaPlugin {
  private TetragonPlugin() {}

  public static final String PREFIX = "";
  public Locations locations;
  public Game game;
  public Items items;
  public ArrayList<ItemStack> lootItems;
  public Messages messages;
  public BlockGenerator blockGenerator;

  @Override
  public void onEnable() {
    getLogger().info("§7Tetragon-Plugin [AdvGames] loaded!");
    initial();
    saveDefaultConfig();
  }

  @Override
  public void onDisable() {
    getLogger().info("§7Tetragon-Plugin [AdvGames] unloaded!");
  }

  private void initial() {
    game = new Game();
    locations = new Locations();
    items = new Items();
    lootItems = new ArrayList<>();
    messages = new Messages(this);
    blockGenerator = new BlockGenerator();
    for(String items : items.getItemsFromFile()) {
      lootItems.add(new ItemStack(Material.valueOf(items)));
    }
    lootItems.add(new ItemStack(Material.EXP_BOTTLE, 8));
    lootItems.add(new ItemStack(Material.APPLE, 5));
    lootItems.add(new ItemStack(Material.COOKED_CHICKEN, 9));
    lootItems.add(new ItemStack(Material.COOKED_MUTTON, 6));
  }
}
