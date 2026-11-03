package com.enderdevelopment.plugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class Main extends JavaPlugin implements Listener {

    private final List<Material> outcomes = new ArrayList<>();
    private final Random random = new Random();
    private final NamespacedKey redstoneKey = new NamespacedKey(this, "redstone_activated");
    
    @Override
    public void onEnable() {
        // Register events and commands
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("cmbi:random").setExecutor(this);
        
        // Initialize outcomes
        initializeOutcomes();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void initializeOutcomes() {
        // Add all vanilla items and blocks to the outcomes list
        for (Material material : Material.values()) {
            if (material.isItem() && material.isBlock()) {
                outcomes.add(material);
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("cmbi:random")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("cmbi.random")) {
                    executeRandomCommand(player);
                } else {
                    player.sendMessage("You do not have permission to use this command.");
                }
            } else {
                sender.sendMessage("This command can only be executed by a player.");
            }
            return true;
        }
        return false;
    }

    @EventHandler
    public void onBlockRedstone(BlockRedstoneEvent event) {
        if (event.getNewCurrent() > 0 && event.getOldCurrent() == 0) {
            // Rising edge detected
            if (event.getBlock().getType() == Material.COMMAND_BLOCK) {
                // Check if the command block contains the cmbi:random command
                String command = event.getBlock().getBlockData().getAsString();
                if (command.contains("cmbi:random")) {
                    // Execute the command
                    executeRandomCommand(null);
                }
            }
        }
    }

    private void executeRandomCommand(Player player) {
        // Select a random outcome
        Material outcome = outcomes.get(random.nextInt(outcomes.size()));
        
        // Determine the amount of items to give
        int amount = 1;
        if (outcome == Material.EGG || outcome == Material.COMMAND_BLOCK) {
            amount = 1;
        } else {
            amount = 6400;
        }
        
        // Create the item stack
        ItemStack itemStack = new ItemStack(outcome, amount);
        
        // Give the item to the player or the nearest online player in the same world
        if (player != null) {
            player.getInventory().addItem(itemStack);
        } else {
            Player nearestPlayer = findNearestOnlinePlayer();
            if (nearestPlayer != null) {
                nearestPlayer.getInventory().addItem(itemStack);
            }
        }
    }

    private Player findNearestOnlinePlayer() {
        Player nearestPlayer = null;
        double minDistance = Double.MAX_VALUE;
        
        for (Player player : Bukkit.getOnlinePlayers()) {
            double distance = player.getLocation().distance(Bukkit.getWorlds().get(0).getSpawnLocation());
            if (distance < minDistance) {
                minDistance = distance;
                nearestPlayer = player;
            }
        }
        
        return nearestPlayer;
    }
}