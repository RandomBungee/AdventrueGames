package com.gitlab.leonklein.listener;

import com.gitlab.leonklein.TetragonPlugin;
import java.util.Random;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class BlockInteractListener implements Listener {
  private final TetragonPlugin tetragonPlugin;
  private final Random random = new Random();

  public BlockInteractListener(TetragonPlugin tetragonPlugin) {
    this.tetragonPlugin = tetragonPlugin;
  }

  @EventHandler
  public void playerInteractWithDiamondBlock(PlayerInteractEvent playerInteractEvent) {
    Player player = playerInteractEvent.getPlayer();
    Block clickedBlock = playerInteractEvent.getClickedBlock();
    if(playerInteractEvent.getAction() != Action.RIGHT_CLICK_BLOCK
        && clickedBlock.getType() != Material.DIAMOND_BLOCK) {
      return;
    }
    clickedBlock.setType(Material.AIR);
    int randomItem = random.nextInt(tetragonPlugin.lootItems.size()) - 1;
    ItemStack itemStack = new ItemStack(tetragonPlugin.lootItems.get(randomItem));
    player.getInventory().addItem(itemStack);
  }
}
