package me.wtfblaze.veinblock.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Taunts implements CommandExecutor
{

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args)
    {
        if (sender instanceof Player)
        {
            Player player = (Player)sender;
            Inventory inv = Bukkit.createInventory(player, 54, "");

            // Pane Borders
            ItemStack whitePane = createGuiItem(Material.WHITE_STAINED_GLASS_PANE, " ");
            ItemStack magentaPane = createGuiItem(Material.MAGENTA_STAINED_GLASS_PANE, " ");
            ItemStack locked = createGuiItem(Material.BLACK_CONCRETE, "&7&l&kLOCKED");

            inv.setItem(0, whitePane);
            inv.setItem(1, magentaPane);
            inv.setItem(2, whitePane);
            inv.setItem(3, magentaPane);
            inv.setItem(4, magentaPane);
            inv.setItem(5, magentaPane);
            inv.setItem(6, whitePane);
            inv.setItem(7, magentaPane);
            inv.setItem(8, whitePane);
            inv.setItem(9, magentaPane);
            inv.setItem(17, magentaPane);
            inv.setItem(18, whitePane);
            inv.setItem(26, whitePane);
            inv.setItem(27, whitePane);
            inv.setItem(35, whitePane);
            inv.setItem(36, magentaPane);
            inv.setItem(44, magentaPane);
            inv.setItem(45, whitePane);
            inv.setItem(46, magentaPane);
            inv.setItem(47, whitePane);
            inv.setItem(48, magentaPane);
            inv.setItem(49, magentaPane);
            inv.setItem(50, magentaPane);
            inv.setItem(51, whitePane);
            inv.setItem(52, magentaPane);
            inv.setItem(53, whitePane);


            if (player.hasPermission("veinblock.taunts.vampirebite"))
                inv.setItem(20, createGuiItem(Material.REDSTONE, "&cVampire Bite", " ", "&7Turns the targeted player into a", "&7vampire for a few seconds", " "));
            else
                inv.setItem(20, locked);

            if (player.hasPermission("veinblock.taunts.beesting"))
                inv.setItem(21, createGuiItem(Material.HONEYCOMB, "&eBee Sting", " ", "&7Stings the targeted player", ""));
            else
                inv.setItem(21, locked);
        }
        return true;
    }

    protected ItemStack createGuiItem(final Material material, final String name, final String... lore)
    {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        assert meta != null;
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));

        // Set the lore of the item
        for (int i = 0; i < lore.length; i++) {
            lore[i] = ChatColor.translateAlternateColorCodes('&', lore[i]);
        }
        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }
}
