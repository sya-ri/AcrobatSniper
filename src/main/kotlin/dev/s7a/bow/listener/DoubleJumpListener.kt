package dev.s7a.bow.listener

import dev.s7a.bow.AcrobatSniper.Companion.plugin
import dev.s7a.bow.util.isAdventureMode
import dev.s7a.bow.util.isCreativeMode
import dev.s7a.spigot.scheduler.runTaskLater
import dev.s7a.spigot.util.VirtualPlayer
import dev.s7a.spigot.util.VirtualPlayer.Companion.toVirtual
import org.bukkit.Effect
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerToggleFlightEvent

/**
 * ダブルジャンプに関するイベントリスナー
 */
object DoubleJumpListener : Listener {
    private val allowFlightPlayers = mutableSetOf<VirtualPlayer>()

    @EventHandler
    fun on(event: PlayerJoinEvent) {
        val player = event.player
        val virtualPlayer = player.toVirtual()
        if (allowFlightPlayers.contains(virtualPlayer)) {
            allowFlightPlayers.remove(virtualPlayer)
            player.allowFlight = false
            player.isFlying = false
        }
    }

    @EventHandler
    fun on(event: PlayerToggleFlightEvent) {
        val player = event.player
        val virtualPlayer = player.toVirtual()
        if (player.isAdventureMode.not()) {
            if (player.isCreativeMode.not()) {
                player.isFlying = false
                player.allowFlight = false
            }
            return
        }
        event.isCancelled = true
        player.allowFlight = false
        player.isFlying = false
        player.velocity = player.location.direction.multiply(1.0).setY(1)
        allowFlightPlayers.add(virtualPlayer)
        val world = player.world
        val location = player.location
        world.playEffect(location, Effect.SMOKE, 5)
        world.playSound(location, Sound.ENTITY_ENDER_DRAGON_SHOOT, SoundCategory.NEUTRAL, 1.0f, 0.0f)
        plugin.runTaskLater(20) {
            if (player.isFlying) {
                player.allowFlight = false
                allowFlightPlayers.remove(virtualPlayer)
            }
        }
    }

    @EventHandler
    fun on(event: PlayerMoveEvent) {
        val player = event.player
        val location = player.location
        val underBlock = location.subtract(0.0, 1.0, 0.0).block
        if (player.isAdventureMode && underBlock.type.isAir.not()) {
            player.allowFlight = true
        }
    }
}
