package me.wtfblaze.veinblock.events;

import me.wtfblaze.veinblock.VeinBlock;
import me.wtfblaze.veinblock.misc.BlockReplaceTask;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreak implements Listener
{
    private final VeinBlock plugin;
    public BlockBreak(VeinBlock plugin) { this.plugin = plugin; }

    @EventHandler
    public void onBlockBroken(BlockBreakEvent e)
    {
        Block block = e.getBlock();
        World world = block.getWorld();
        if (world.getName().equalsIgnoreCase("PublicMine"))
        {
            Player player = e.getPlayer();
            if (IsNormalOre(block.getBlockData().getMaterial()))
            {
                if (player.getGameMode() == GameMode.SURVIVAL)
                {
                    e.setCancelled(true);
                    Material originalMaterial = block.getType();
                    for (ItemStack drop : e.getBlock().getDrops()) {
                        player.getInventory().addItem(drop);
                    }
                    block.setType(Material.BEDROCK);
                    new BlockReplaceTask(block, originalMaterial).runTaskTimer(plugin, 3600, 20);
                }
            }
            else
            {
                if (player.getGameMode() == GameMode.SURVIVAL)
                {
                    e.setCancelled(true);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&lMINES &8&l» &7You can only mine ores in the public mine!"));
                }
            }
        }
    }

    private boolean IsNormalOre(Material block)
    {
        return block == Material.COAL_ORE || block == Material.COPPER_ORE || block == Material.GOLD_ORE ||
                block == Material.LAPIS_ORE || block == Material.REDSTONE_ORE || block == Material.IRON_ORE ||
                block == Material.DIAMOND_ORE || block == Material.EMERALD_ORE;
    }
}
