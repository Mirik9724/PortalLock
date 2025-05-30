package net.Mirik9724.portalLock.Bukkit

import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.world.PortalCreateEvent

class PortalLockListener(private val plugin: PortalLock) : Listener {

    private val overworldRadius: Int
        get() = plugin.config.getInt("over_radius", 10000)
    private val netherRadius: Int
        get() = plugin.config.getInt("nether_radium", 2000)

    private fun isInsideRadius(world: World, x: Int, z: Int): Boolean {
        val radius = when (world.environment) {
            World.Environment.NORMAL -> overworldRadius
            World.Environment.NETHER -> netherRadius
            else -> return true // не блокируем в других мирах
        }

        return x in -radius..radius && z in -radius..radius
    }

    @EventHandler
    fun onPortalCreate(event: PortalCreateEvent) {
        val blocks = event.blocks
        if (blocks.isEmpty()) return

        val block = blocks[0]
        val world = block.world

        if (!isInsideRadius(world, block.x, block.z)) {
//            val msg = plugin.config.getString("messages.out_of_range", "§cYou can't create portals outside allowed area!")
            event.isCancelled = true
//            Bukkit.getLogger().info("Portal creation blocked at (${block.x}, ${block.z}) in ${world.name}")
        }
    }
}
