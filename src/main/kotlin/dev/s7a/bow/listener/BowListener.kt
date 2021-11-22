package dev.s7a.bow.listener

import dev.s7a.spigot.util.color
import dev.s7a.spigot.util.sendActionBarMessage
import org.bukkit.entity.Arrow
import org.bukkit.entity.Player
import org.bukkit.entity.Projectile
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.PlayerDeathEvent

/**
 * 弓に関するイベントリスナー
 */
object BowListener : Listener {
    @EventHandler(ignoreCancelled = true)
    fun on(event: EntityDamageByEntityEvent) {
        val hitEntity = event.entity as? Player ?: return
        val arrow = event.damager as? Arrow ?: return
        val shooter = arrow.shooter as? Player ?: return
        val damage = event.damage
        val isKilled = (hitEntity.health - damage) < 0.0
        if (isKilled) {
            shooter.sendActionBarMessage("&6&lKill / ${shooter.name} → ${hitEntity.name}")
        } else {
            shooter.sendActionBarMessage("&6&lHit -$damage / ${shooter.name} → ${hitEntity.name}")
            shooter.sendActionBarMessage("&c&lHit -$damage / ${shooter.name} → ${hitEntity.name}")
        }
    }

    @EventHandler
    fun on(event: PlayerDeathEvent) {
        event.keepInventory = true
        val player = event.entity
        val lastDamageCause = player.lastDamageCause as? EntityDamageByEntityEvent
        val killer = when (val damager = lastDamageCause?.damager) {
            is Projectile -> damager.shooter as? Player
            else -> damager as? Player
        }
        val killerName = killer?.name ?: "???"
        event.deathMessage = "&7≫ &cKill $killerName → ${player.name}".color()
    }
}
