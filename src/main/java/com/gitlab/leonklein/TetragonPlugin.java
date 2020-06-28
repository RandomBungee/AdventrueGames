package com.gitlab.leonklein;

import com.gitlab.leonklein.command.SetupCommand;
import com.gitlab.leonklein.configuration.Items;
import com.gitlab.leonklein.configuration.Locations;
import com.gitlab.leonklein.configuration.Messages;
import com.gitlab.leonklein.game.BlockGenerator;
import com.gitlab.leonklein.game.Game;
import com.gitlab.leonklein.listener.BlockInteractListener;
import com.gitlab.leonklein.listener.CompassInventoryInteract;
import com.gitlab.leonklein.listener.DamageListener;
import com.gitlab.leonklein.listener.JoinListener;
import com.gitlab.leonklein.listener.PlayerDeath;
import com.gitlab.leonklein.listener.QuitListener;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TetragonPlugin extends JavaPlugin {
  private TetragonPlugin() {}

  public static String prefix;
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
    prefix = getConfig().getString("prefix")
        .replaceAll("&", "§");
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
    registerCommands();
    registerListener();
  }

  private void registerCommands() {
    getCommand("setup").setExecutor(new SetupCommand(this));
  }

  private void registerListener() {
    PluginManager pluginManager = Bukkit.getPluginManager();
    pluginManager.registerEvents(new BlockInteractListener(this), this);
    pluginManager.registerEvents(new CompassInventoryInteract(this), this);
    pluginManager.registerEvents(new DamageListener(this), this);
    pluginManager.registerEvents(new JoinListener(this), this);
    pluginManager.registerEvents(new PlayerDeath(this), this);
    pluginManager.registerEvents(new QuitListener(this), this);
  }
}
