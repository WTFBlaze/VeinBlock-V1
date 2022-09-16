package me.wtfblaze.veinblock.events;

import me.wtfblaze.veinblock.VeinBlock;
import me.wtfblaze.veinblock.objects.TauntCooldown;
import me.wtfblaze.veinblock.objects.TauntType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class Chat implements Listener
{
    private final VeinBlock plugin;
    private final List<String> blockedCharacters = Arrays.asList("丩", "个", "丫", "丬", "中", "丮", "丯", "丰", "ꌣ");
    public Chat(VeinBlock plugin) { this.plugin = plugin; }
    private final String[] ballAnswers = new String[]
            {
                    "It is certain.",
                    "Without a doubt.", "You may rely on it.",
                    "As I see it, yes.", "Ask again later.",
                    "Better not tell you now.", "Cannot predict now.",
                    "Don't count on it.", "My reply is no.",
                    "My sources say no.", "Outlook not so good."
            };


    @EventHandler
    public void onChat(AsyncPlayerChatEvent e)
    {
        for (String character : blockedCharacters)
        {
            if (e.getMessage().contains(character))
            {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&lSERVER &8&l» &7You cannot use that character here!"));
            }
        }

        if (plugin.waitingForTauntTarget.containsKey(e.getPlayer().getUniqueId()))
        {
            e.setCancelled(true);
            TauntType type = plugin.waitingForTauntTarget.get(e.getPlayer().getUniqueId());
            if (type == TauntType.MAGIC_8_BALL)
            {
                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', String.format("&d&lTAUNTS &8&l» &b%s &7asked 8Ball: &e%s&7!", e.getPlayer().getName(), e.getMessage())));
                int responseNumb = new Random().nextInt(ballAnswers.length);
                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', String.format("&d&lTAUNTS &8&l» &b8Ball &7Says: &e%s", ballAnswers[responseNumb])));
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.MINUTE, 10);
                plugin.tauntCooldowns.add(new TauntCooldown(calendar, e.getPlayer().getUniqueId(), type));
            }
            else
            {
                Player target = plugin.getServer().getPlayer(e.getMessage());
                if (target == null) {
                    e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&lTAUNTS &8&l» &7That player could not be found!"));
                } else {
                    switch (type) {
                        case VAMPIRE_BITE: {
                            plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', String.format("&d&lTAUNTS &8&l» &b%s &7has bitten &b%s &7and turned them into a &cVampire&7!", e.getPlayer().getName(), target.getName())));
                            ItemStack item = new ItemStack(Material.GHAST_TEAR);
                            ItemMeta meta = item.getItemMeta();
                            assert meta != null;
                            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', String.format("&c&l%s's Fang", e.getPlayer().getName())));
                            item.setItemMeta(meta);
                            target.getInventory().addItem(item);
                            Calendar calendar = Calendar.getInstance();
                            calendar.add(Calendar.MINUTE, 10);
                            plugin.tauntCooldowns.add(new TauntCooldown(calendar, e.getPlayer().getUniqueId(), type));
                        }
                        break;

                        case BEE_STING: {
                            plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', String.format("&d&lTAUNTS &8&l» &b%s &7has stung &b%s &7with their stinger!", e.getPlayer().getName(), target.getName())));
                            ItemStack item = new ItemStack(Material.ARROW);
                            ItemMeta meta = item.getItemMeta();
                            assert meta != null;
                            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', String.format("&e&l%s's Stinger", e.getPlayer().getName())));
                            item.setItemMeta(meta);
                            target.getInventory().addItem(item);
                            Calendar calendar = Calendar.getInstance();
                            calendar.add(Calendar.MINUTE, 10);
                            plugin.tauntCooldowns.add(new TauntCooldown(calendar, e.getPlayer().getUniqueId(), type));
                        }
                        break;

                        case KISS: {
                            plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', String.format("&d&lTAUNTS &8&l» &b%s &7has blown &b%s &7a kiss!", e.getPlayer().getName(), target.getName())));
                            Calendar calendar = Calendar.getInstance();
                            calendar.add(Calendar.MINUTE, 10);
                            plugin.tauntCooldowns.add(new TauntCooldown(calendar, e.getPlayer().getUniqueId(), type));
                        }
                        break;

                        case BONK: {
                            plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', String.format("&d&lTAUNTS &8&l» &b%s &7has bonked &b%s &7!", e.getPlayer().getName(), target.getName())));
                            Calendar calendar = Calendar.getInstance();
                            calendar.add(Calendar.MINUTE, 10);
                            plugin.tauntCooldowns.add(new TauntCooldown(calendar, e.getPlayer().getUniqueId(), type));
                        }
                        break;

                        case GIVE_A_ROSE: {
                            plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', String.format("&d&lTAUNTS &8&l» &b%s &7has given &b%s &7a rose!", e.getPlayer().getName(), target.getName())));
                            ItemStack item = new ItemStack(Material.POPPY);
                            ItemMeta meta = item.getItemMeta();
                            assert meta != null;
                            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', String.format("&e&l%s's Rose", e.getPlayer().getName())));
                            item.setItemMeta(meta);
                            target.getInventory().addItem(item);
                            Calendar calendar = Calendar.getInstance();
                            calendar.add(Calendar.MINUTE, 10);
                            plugin.tauntCooldowns.add(new TauntCooldown(calendar, e.getPlayer().getUniqueId(), type));
                        }
                        break;

                        case PAY_RESPECTS: {
                            plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', String.format("&d&lTAUNTS &8&l» &b%s &7has paid their respects to &b%s&7. F in the chats plz!", e.getPlayer().getName(), target.getName())));
                            Calendar calendar = Calendar.getInstance();
                            calendar.add(Calendar.MINUTE, 10);
                            plugin.tauntCooldowns.add(new TauntCooldown(calendar, e.getPlayer().getUniqueId(), type));
                        }
                        break;

                        case JUMP_SCARE: {
                            plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', String.format("&d&lTAUNTS &8&l» &b%s &7has jumpscared &b%s&7!", e.getPlayer().getName(), target.getName())));
                            target.playSound(target, Sound.ENTITY_CREEPER_PRIMED, 3, 1);
                            Calendar calendar = Calendar.getInstance();
                            calendar.add(Calendar.MINUTE, 10);
                            plugin.tauntCooldowns.add(new TauntCooldown(calendar, e.getPlayer().getUniqueId(), type));
                        }
                        break;
                    }
                }
            }
        }
    }
}
