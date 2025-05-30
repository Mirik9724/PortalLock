package net.Mirik9724.portalLock.Bukkit

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class ComReload(private val plugin: PortalLock) : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val noPermMsg = plugin.config.getString("messages.no_permission", "§cYou don't have permission to execute this command.")
        val reloadSuccessMsg = plugin.config.getString("messages.reload_success", "§aPortalLock config reloaded!")

        if (!sender.hasPermission("portallock.reload")) {
            sender.sendMessage(noPermMsg)
            return true
        }

        plugin.reloadConfig()
        sender.sendMessage(reloadSuccessMsg)
//        plugin.logger_.info("Config reloaded by ${sender.name}")

        return true
    }
}
