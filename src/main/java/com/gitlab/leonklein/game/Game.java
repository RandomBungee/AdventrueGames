package com.gitlab.leonklein.game;

import java.util.ArrayList;
import org.bukkit.entity.Player;

public class Game {
  public Game() {}

  public ArrayList<Player> players = new ArrayList<>();
  public ArrayList<Player> spectators = new ArrayList<>();
  private GameState gameState;

  public void checkForWinner() {
    if(players.size() != 1) {
      return;
    }
    Player gameWinner = players.get(0);
    gameWinner.sendMessage("§aDu hast gewonnen!");
    setGameState(GameState.END);
  }

  public void setGameState(GameState gameState) {
    this.gameState = gameState;
  }

  public GameState gameState() {
    return this.gameState;
  }
}
