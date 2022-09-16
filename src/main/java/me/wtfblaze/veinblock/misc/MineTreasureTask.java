package me.wtfblaze.veinblock.misc;

import me.wtfblaze.veinblock.VeinBlock;
import me.wtfblaze.veinblock.objects.MineEntrance;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class MineTreasureTask extends BukkitRunnable
{
    private final VeinBlock plugin;
    public MineTreasureTask(VeinBlock plugin) { this.plugin = plugin; }

    @Override
    public void run()
    {
        int chamber = new Random().nextInt(3 - 1 + 1) + 1;

        switch (chamber)
        {
            default:
            {
                Location[] triggerLocations = new Location[]
                        {
                                new Location(plugin.getServer().getWorld("PublicMine"), -99, 35, 39),
                                new Location(plugin.getServer().getWorld("PublicMine"), -98, 35, 39),
                                new Location(plugin.getServer().getWorld("PublicMine"), -97, 35, 39),
                        };

                Location[] doorLocations = new Location[]
                        {
                                // top row
                                new Location(plugin.getServer().getWorld("PublicMine"), -99, 37, 40),
                                new Location(plugin.getServer().getWorld("PublicMine"), -98, 37, 40),
                                new Location(plugin.getServer().getWorld("PublicMine"), -97, 37, 40),

                                // middle row
                                new Location(plugin.getServer().getWorld("PublicMine"), -99, 36, 40),
                                new Location(plugin.getServer().getWorld("PublicMine"), -98, 36, 40),
                                new Location(plugin.getServer().getWorld("PublicMine"), -97, 36, 40),

                                // bottom row
                                new Location(plugin.getServer().getWorld("PublicMine"), -99, 35, 40),
                                new Location(plugin.getServer().getWorld("PublicMine"), -98, 35, 40),
                                new Location(plugin.getServer().getWorld("PublicMine"), -97, 35, 40),
                        };

                Location chestLocation = new Location(plugin.getServer().getWorld("PublicMine"), -103, 34, 31);
                plugin.currentMineEntrance = new MineEntrance(triggerLocations, doorLocations, chestLocation);
                for (Location doorBlock : doorLocations)
                {
                    doorBlock.getBlock().setType(Material.AIR);
                }
            }
            break;

            case 2:
            {
                Location[] triggerLocations = new Location[]
                        {
                                new Location(plugin.getServer().getWorld("PublicMine"), -92, 30, 61),
                                new Location(plugin.getServer().getWorld("PublicMine"), -93, 30, 61),
                                new Location(plugin.getServer().getWorld("PublicMine"), -94, 30, 61),
                        };

                Location[] doorLocations = new Location[]
                        {
                                // top row
                                new Location(plugin.getServer().getWorld("PublicMine"), -92, 32, 60),
                                new Location(plugin.getServer().getWorld("PublicMine"), -93, 32, 60),
                                new Location(plugin.getServer().getWorld("PublicMine"), -94, 32, 60),

                                // middle row
                                new Location(plugin.getServer().getWorld("PublicMine"), -92, 31, 60),
                                new Location(plugin.getServer().getWorld("PublicMine"), -93, 31, 60),
                                new Location(plugin.getServer().getWorld("PublicMine"), -94, 31, 60),

                                // bottom row
                                new Location(plugin.getServer().getWorld("PublicMine"), -92, 30, 60),
                                new Location(plugin.getServer().getWorld("PublicMine"), -93, 30, 60),
                                new Location(plugin.getServer().getWorld("PublicMine"), -94, 30, 60),
                        };

                Location chestLocation = new Location(plugin.getServer().getWorld("PublicMine"), -96, 29, 68);
                plugin.currentMineEntrance = new MineEntrance(triggerLocations, doorLocations, chestLocation);
                for (Location doorBlock : doorLocations)
                {
                    doorBlock.getBlock().setType(Material.AIR);
                }
            }
            break;

            case 3:
            {
                Location[] triggerLocations = new Location[]
                        {
                                new Location(plugin.getServer().getWorld("PublicMine"), -105, 14, 16),
                                new Location(plugin.getServer().getWorld("PublicMine"), -106, 14, 16),
                                new Location(plugin.getServer().getWorld("PublicMine"), -107, 14, 16),
                        };

                Location[] doorLocations = new Location[]
                        {
                                // top row
                                new Location(plugin.getServer().getWorld("PublicMine"), -105, 16, 15),
                                new Location(plugin.getServer().getWorld("PublicMine"), -106, 16, 15),
                                new Location(plugin.getServer().getWorld("PublicMine"), -107, 16, 15),

                                // middle row
                                new Location(plugin.getServer().getWorld("PublicMine"), -105, 15, 15),
                                new Location(plugin.getServer().getWorld("PublicMine"), -106, 15, 15),
                                new Location(plugin.getServer().getWorld("PublicMine"), -107, 15, 15),

                                // bottom row
                                new Location(plugin.getServer().getWorld("PublicMine"), -105, 14, 15),
                                new Location(plugin.getServer().getWorld("PublicMine"), -106, 14, 15),
                                new Location(plugin.getServer().getWorld("PublicMine"), -107, 14, 15),
                        };

                Location chestLocation = new Location(plugin.getServer().getWorld("PublicMine"), -108, 13, 22);
                plugin.currentMineEntrance = new MineEntrance(triggerLocations, doorLocations, chestLocation);
                for (Location doorBlock : doorLocations)
                {
                    doorBlock.getBlock().setType(Material.AIR);
                }
            }
            break;
        }

        this.cancel();
    }
}
