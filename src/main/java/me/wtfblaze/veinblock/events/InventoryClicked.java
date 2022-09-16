package me.wtfblaze.veinblock.events;

import me.wtfblaze.veinblock.VeinBlock;
import me.wtfblaze.veinblock.objects.TauntCooldown;
import me.wtfblaze.veinblock.objects.TauntType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Calendar;

public class InventoryClicked implements Listener
{
    private final VeinBlock plugin;
    public InventoryClicked(VeinBlock plugin) { this.plugin = plugin; }

    @EventHandler
    public void onInventoryClicked(InventoryClickEvent e)
    {
        if (plugin.openedTauntsInventories.contains(e.getInventory()))
        {
            e.setCancelled(true);

            if (e.getCurrentItem() != null)
            {
                Material type = e.getCurrentItem().getType();
                if (type != Material.BLACK_CONCRETE && type != Material.WHITE_STAINED_GLASS_PANE && type != Material.MAGENTA_STAINED_GLASS_PANE)
                {
                    Player player = (Player)e.getWhoClicked();
                    switch (e.getSlot())
                    {
                        case 20:
                        {
                            for (TauntCooldown tc : plugin.tauntCooldowns)
                            {
                                if (tc.uuid == e.getWhoClicked().getUniqueId())
                                {
                                    if (tc.tauntType == TauntType.VAMPIRE_BITE)
                                    {
                                        if (tc.calendar.before(Calendar.getInstance()))
                                        {
                                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&lTAUNTS &8&l» &7You cannot use that taunt yet!"));
                                            break;
                                        }
                                        else
                                        {
                                            plugin.tauntCooldowns.remove(tc);
                                            plugin.waitingForTauntTarget.put(player.getUniqueId(), TauntType.VAMPIRE_BITE);
                                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&lTAUNTS &8&l» &7Please type your target's username in chat"));
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        break;

                        case 21:
                        {
                            for (TauntCooldown tc : plugin.tauntCooldowns)
                            {
                                if (tc.uuid == e.getWhoClicked().getUniqueId())
                                {
                                    if (tc.tauntType == TauntType.BEE_STING)
                                    {
                                        if (tc.calendar.before(Calendar.getInstance()))
                                        {
                                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&lTAUNTS &8&l» &7You cannot use that taunt yet!"));
                                        }
                                        else
                                        {
                                            plugin.waitingForTauntTarget.put(player.getUniqueId(), TauntType.BEE_STING);
                                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&lTAUNTS &8&l» &7Please type your target's username in chat"));
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    }
                }
            }
        }
    }
}
