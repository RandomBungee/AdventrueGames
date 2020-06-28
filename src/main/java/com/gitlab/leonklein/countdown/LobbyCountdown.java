package com.gitlab.leonklein.countdown;

import com.gitlab.leonklein.TetragonPlugin;
import com.gitlab.leonklein.game.GameState;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class LobbyCountdown {
  private int count;
  private BukkitRunnable bukkitRunnable;
  private boolean completed;
  private TetragonPlugin tetragonPlugin;

  public LobbyCountdown(
      int count,
      TetragonPlugin tetragonPlugin
  ) {
    this.count = count;
    this.tetragonPlugin = tetragonPlugin;
  }

  public void startCountDown(Plugin plugin) {
    this.bukkitRunnable = new BukkitRunnable() {
      @Override
      public void run() {
        updateCount();
        if(completed) {
          cancel();
        }
      }
    };
    this.bukkitRunnable.runTaskTimer(plugin, 20, 20);
  }

  private void updateCount() {
    switch (this.count) {
      case 60, 30, 20, 10, 5, 4, 3, 2 -> Bukkit
          .broadcastMessage(TetragonPlugin.PREFIX + tetragonPlugin.messages.messageFromConfig("countdown1"));
      case 1 -> Bukkit.broadcastMessage(TetragonPlugin.PREFIX + tetragonPlugin.messages.messageFromConfig("countdown2"));
      case 0 -> {
        Bukkit.broadcastMessage(TetragonPlugin.PREFIX + "§aDie Runde beginnt!");
        complete();
      }
    }
    this.count--;
  }

  public void cancel() {
    this.bukkitRunnable.cancel();
    Bukkit.broadcastMessage("NIX GENUG SPIELER");
  }

  private void complete() {
    this.completed = true;
    this.bukkitRunnable.cancel();
    Bukkit.getOnlinePlayers().forEach(players -> players.teleport(Bukkit.getWorld("world").getSpawnLocation()));
    tetragonPlugin.game.setGameState(GameState.GAME);
    tetragonPlugin.blockGenerator.generateBlock(Bukkit.getWorld("world"), tetragonPlugin);
  }
}
