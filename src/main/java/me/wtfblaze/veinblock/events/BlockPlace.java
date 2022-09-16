package me.wtfblaze.veinblock.events;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener
{
    @EventHandler
    public void onBlockPlaced(BlockPlaceEvent e)
    {
        World world = e.getBlock().getWorld();
        if (world.getName().equalsIgnoreCase("PublicMine"))
        {
            Player player = e.getPlayer();
            if (player.getGameMode() == GameMode.SURVIVAL)
            {
                e.setCancelled(true);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&lMINES &8&l» &7You cannot place blocks in the public mine!"));
            }
        }
    }
}
