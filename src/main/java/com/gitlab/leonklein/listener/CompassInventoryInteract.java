package com.gitlab.leonklein.listener;

import com.gitlab.leonklein.TetragonPlugin;
import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class CompassInventoryInteract implements Listener {
  private final TetragonPlugin tetragonPlugin;
  private final Inventory inventory = Bukkit.createInventory(null, 27, "§cKompass");

  public CompassInventoryInteract(TetragonPlugin tetragonPlugin) {
    this.tetragonPlugin = tetragonPlugin;
  }

  @EventHandler
  public void openCompassInventory(PlayerInteractEvent playerInteractEvent) {
    Player player = playerInteractEvent.getPlayer();
    if(!tetragonPlugin.game.spectators.contains(player)
        && !(playerInteractEvent.getAction() == Action.RIGHT_CLICK_AIR)) {
      return;
    }
    for(Player players : tetragonPlugin.game.players) {
      inventory.addItem(loadHead(players.getName()));
    }
  }

  @EventHandler
  public void teleportSpectatorToPlayer(InventoryClickEvent inventoryClickEvent) {
    Player player = (Player)inventoryClickEvent.getWhoClicked();
    if(!inventoryClickEvent.getInventory().getName().equalsIgnoreCase("§cKompass")) {
      return;
    }
    Player toTeleport = Bukkit.getPlayer(
        inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().replaceAll("§6", ""));
    player.teleport(toTeleport.getLocation());
    player.closeInventory();
  }

  private ItemStack loadHead(String name) {
    ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
    SkullMeta skullMeta = (SkullMeta)itemStack.getItemMeta();
    skullMeta.setOwner(name);
    skullMeta.setDisplayName("§6" + name);
    itemStack.setItemMeta(skullMeta);
    return itemStack;
  }
}
