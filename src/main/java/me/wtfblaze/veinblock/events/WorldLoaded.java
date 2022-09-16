package me.wtfblaze.veinblock.events;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.function.pattern.RandomPattern;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.world.block.BaseBlock;
import com.sk89q.worldedit.world.block.BlockState;
import com.sk89q.worldedit.world.block.BlockTypes;
import me.wtfblaze.veinblock.VeinBlock;
import me.wtfblaze.veinblock.misc.MineTreasureTask;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;

import java.util.HashSet;
import java.util.Set;

public class WorldLoaded implements Listener
{
    private final VeinBlock plugin;
    public WorldLoaded(VeinBlock plugin) { this.plugin = plugin; }

    @EventHandler
    public void onWorldLoaded(WorldLoadEvent e)
    {
        if (e.getWorld().getName().equalsIgnoreCase("PublicMine"))
        {
            World world = e.getWorld();

            // Create List of blocks we want to look for
            Set<BaseBlock> s1 = new HashSet<>();
            assert BlockTypes.BEDROCK != null;
            s1.add(BlockTypes.BEDROCK.getDefaultState().toBaseBlock());

            // Define the region to look in
            CuboidRegion cr = new CuboidRegion(BukkitAdapter.adapt(world), BlockVector3.at(-152, 51, 82), BlockVector3.at(-8, -15, -24));

            try (EditSession editSession = WorldEdit.getInstance().newEditSession(cr.getWorld()))
            {
                // Create Pattern
                RandomPattern pattern = new RandomPattern();

                // Get Block States of the ores we want
                BlockState iron = BukkitAdapter.adapt(Material.IRON_ORE.createBlockData());
                BlockState copper = BukkitAdapter.adapt(Material.COPPER_ORE.createBlockData());
                BlockState gold = BukkitAdapter.adapt(Material.GOLD_ORE.createBlockData());
                BlockState redstone = BukkitAdapter.adapt(Material.REDSTONE_ORE.createBlockData());
                BlockState emerald = BukkitAdapter.adapt(Material.EMERALD_ORE.createBlockData());
                BlockState lapis = BukkitAdapter.adapt(Material.LAPIS_ORE.createBlockData());
                BlockState diamond = BukkitAdapter.adapt(Material.DIAMOND_ORE.createBlockData());

                // Add ores to the pattern
                pattern.add(iron, 0.5);
                pattern.add(copper, 0.5);
                pattern.add(gold, 0.5);
                pattern.add(redstone, 0.5);
                pattern.add(emerald, 0.5);
                pattern.add(lapis, 0.5);
                pattern.add(diamond, 0.5);
                pattern.add(copper, 0.5);
                pattern.add(copper, 0.5);

                // replace the blocks
                plugin.Log(String.format("Changed %s bedrock blocks back to ore!", editSession.replaceBlocks(cr, s1, pattern)));
            }
            catch (MaxChangedBlocksException ex) {
                ex.printStackTrace();
            }


            // reset all doors
            Location[] doorLocations = new Location[]
                    {
                            // Chamber #1
                            new Location(world, -99, 37, 40),
                            new Location(world, -98, 37, 40),
                            new Location(world, -97, 37, 40),
                            new Location(world, -99, 36, 40),
                            new Location(world, -98, 36, 40),
                            new Location(world, -97, 36, 40),
                            new Location(world, -99, 35, 40),
                            new Location(world, -98, 35, 40),
                            new Location(world, -97, 35, 40),

                            // Chamber #2
                            new Location(world, -92, 32, 60),
                            new Location(world, -93, 32, 60),
                            new Location(world, -94, 32, 60),
                            new Location(world, -92, 31, 60),
                            new Location(world, -93, 31, 60),
                            new Location(world, -94, 31, 60),
                            new Location(world, -92, 30, 60),
                            new Location(world, -93, 30, 60),
                            new Location(world, -94, 30, 60),

                            // Chamber #3
                            new Location(world, -105, 16, 15),
                            new Location(world, -106, 16, 15),
                            new Location(world, -107, 16, 15),
                            new Location(world, -105, 15, 15),
                            new Location(world, -106, 15, 15),
                            new Location(world, -107, 15, 15),
                            new Location(world, -105, 14, 15),
                            new Location(world, -106, 14, 15),
                            new Location(world, -107, 14, 15),
                    };

            for (Location doorBlock : doorLocations)
            {
                doorBlock.getBlock().setType(Material.STONE);
            }

            new MineTreasureTask(plugin).runTask(plugin);
        }
    }
}
