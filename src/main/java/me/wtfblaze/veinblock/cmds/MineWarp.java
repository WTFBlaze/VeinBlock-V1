package me.wtfblaze.veinblock.cmds;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MineWarp implements CommandExecutor
{
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args)
    {
        if (sender instanceof Player)
        {
            Player player = (Player)sender;
            Location location = new Location(Bukkit.getWorld("PublicMine"), -109, 40, 51);
            player.teleport(location);
        }
        return true;
    }
}
