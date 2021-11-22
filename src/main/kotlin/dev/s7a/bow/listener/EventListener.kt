package dev.s7a.bow.listener

import dev.s7a.bow.util.Bow
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack

/**
 * イベントリスナー
 */
object EventListener : Listener {
    /**
     * サーバー参加時に弓を渡す
     */
    @EventHandler
    fun on(event: PlayerJoinEvent) {
        val player = event.player
        player.inventory.run {
            clear()
            setItem(0, Bow)
            setItem(27, ItemStack(Material.ARROW))
        }
    }
}
