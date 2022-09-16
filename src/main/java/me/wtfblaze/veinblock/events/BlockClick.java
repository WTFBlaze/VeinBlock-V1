package me.wtfblaze.veinblock.events;

import me.wtfblaze.veinblock.VeinBlock;
import me.wtfblaze.veinblock.misc.MineTreasureTask;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class BlockClick implements Listener
{
    private final VeinBlock plugin;
    public BlockClick(VeinBlock plugin) { this.plugin = plugin; }

    @EventHandler
    public void onBlockClick(PlayerInteractEvent e)
    {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
        {
            if (e.getClickedBlock() != null)
            {
                if (e.getClickedBlock().getType().equals(Material.CHEST))
                {
                    Player player = e.getPlayer();
                    if (player.getWorld().getName().equalsIgnoreCase("PublicMine"))
                    {
                        if (e.getClickedBlock().getLocation().equals(plugin.currentMineEntrance.chestLocation))
                        {
                            e.setCancelled(true);
                            RedeemTreasure(player);
                        }
                    }
                }
            }
        }
    }

    private void RedeemTreasure(Player player)
    {
        int itemCount = new Random().nextInt(4 - 3 + 1) + 3;
        for (int i = 0; i < itemCount; i++)
        {
            GenerateReward(player);
        }
        plugin.currentMineEntrance.chestLocation.getBlock().setType(Material.AIR);
        player.performCommand("mine");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&lMINES &8&l» &b") + player.getName() + ChatColor.translateAlternateColorCodes('&', " &7has found the treasure in the mine!"));
        new MineTreasureTask(plugin).runTaskTimer(plugin, 18000, 20);
    }

    private void GenerateReward(Player player)
    {
        int itemNumber = new Random().nextInt(15 - 1 + 1) + 1;
        switch (itemNumber)
        {
            default:
            {
                Bukkit.dispatchCommand(plugin.getServer().getConsoleSender(),
                        "ac pouch give " + player.getName() + "  Coins_Small");
            }
            break;

            case 2:
            {
                Bukkit.dispatchCommand(plugin.getServer().getConsoleSender(),
                        "ac pouch give " + player.getName() + "  Shards_Small");
            }
            break;

            case 3:
            {
                Bukkit.dispatchCommand(plugin.getServer().getConsoleSender(),
                        "ac pouch give " + player.getName() + "  Coins_Medium");
            }
            break;

            case 4:
            {
                Bukkit.dispatchCommand(plugin.getServer().getConsoleSender(),
                        "ac pouch give " + player.getName() + "  Shards_Medium");
            }
            break;

            case 5:
            {
                Bukkit.dispatchCommand(plugin.getServer().getConsoleSender(),
                        "ac pouch give " + player.getName() + "  Coins_Large");
            }
            break;

            case 6:
            {
                Bukkit.dispatchCommand(plugin.getServer().getConsoleSender(),
                        "ac pouch give " + player.getName() + "  Shards_Large");
            }
            break;

            case 7:
            {
                Bukkit.dispatchCommand(plugin.getServer().getConsoleSender(),
                        "ac pouch give " + player.getName() + "  Coins_XL");
            }
            break;

            case 8:
            {
                Bukkit.dispatchCommand(plugin.getServer().getConsoleSender(),
                        "ac pouch give " + player.getName() + "  Shards_XL");
            }
            break;

            case 9:
            {
                Bukkit.dispatchCommand(plugin.getServer().getConsoleSender(),
                        "ac pouch give " + player.getName() + "  Boosters");
            }
            break;

            case 10:
            {
                Bukkit.dispatchCommand(plugin.getServer().getConsoleSender(),
                        "ac key give " + player.getName() + "  Vote 1");
            }
            break;

            case 11:
            {
                Bukkit.dispatchCommand(plugin.getServer().getConsoleSender(),
                        "ac key give " + player.getName() + "  Cosmetics 1");
            }
            break;

            case 12:
            {
                Bukkit.dispatchCommand(plugin.getServer().getConsoleSender(),
                        "ac key give " + player.getName() + "  Pets 1");
            }
            break;

            case 13:
            {
                ItemStack diamondBlocks = new ItemStack(Material.DIAMOND_BLOCK);
                diamondBlocks.setAmount(12);
                player.getInventory().addItem(diamondBlocks);
            }
            break;

            case 14:
            {
                ItemStack diamondBlocks = new ItemStack(Material.AMETHYST_BLOCK);
                diamondBlocks.setAmount(5);
                player.getInventory().addItem(diamondBlocks);
            }
            break;

            case 15:
            {
                Bukkit.dispatchCommand(plugin.getServer().getConsoleSender(),
                        "/iv give " + player.getName() + "  Tags_Treasure 1");
            }
            break;
        }
    }

    /*
    * Default / 1: Small Coins Pouch
    * 2: Small Shards Pouch
    * 3: Medium Coins Pouch
    * 4: Medium Shards Pouch
    * 5: Large Coins Pouch
    * 6: Large Shards Pouch
    * 7: XL Coins Pouch
    * 8: XL Shards Pouch
    * 9: Boosters Pouch
    * 10: 1x Vote Key
    * 11: 1x Cosmetics Key
    * 12: 1x Pets Key
    * 13: 12x Diamond Block
    * 14: 5x Amethyst
    * 15: Treasure Tag Voucher
    * */
}
