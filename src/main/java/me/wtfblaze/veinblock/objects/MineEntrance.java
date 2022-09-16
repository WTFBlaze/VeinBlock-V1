package me.wtfblaze.veinblock.objects;

import org.bukkit.Location;

public class MineEntrance
{
    public MineEntrance(Location[] triggers, Location[] doors, Location chest)
    {
        triggerLocations = triggers;
        doorLocations = doors;
        chestLocation = chest;
    }

    public Location[] triggerLocations;
    public Location[] doorLocations;
    public Location chestLocation;
    public boolean sentDoorMsg = false;
}
