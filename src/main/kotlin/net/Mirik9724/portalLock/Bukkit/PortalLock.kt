package net.Mirik9724.portalLock.Bukkit

import net.Mirik9724.portalLock.log.logger_
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.plugin.java.JavaPlugin


class PortalLock : JavaPlugin() {
    override fun onEnable() {
        saveDefaultConfig() // создаёт config.yml, если его нет

        val defaults = mapOf(
            "over_radius" to 10000,
            "nether_radium" to 2000
        )

        val config: FileConfiguration = config
        var changed = false

        for ((key, value) in defaults) {
            if (!config.contains(key)) {
                config.set(key, value)
                changed = true
                logger_.info("Added missing config entry: $key = $value")
            }
        }

        if (changed) {
            saveConfig()
            logger_.info("Configuration has been updated and saved.")
        }

        this.getCommand("reload")?.setExecutor(ComReload(this))
        server.pluginManager.registerEvents(PortalLockListener(this), this)

        logger_.info("Plugin ON")
    }

    override fun onDisable() {
        logger_.info("Plug OFF")
    }
}
