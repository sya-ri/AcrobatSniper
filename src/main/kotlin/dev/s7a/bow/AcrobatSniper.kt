package dev.s7a.bow

import dev.s7a.bow.listener.BowListener
import dev.s7a.bow.listener.DoubleJumpListener
import dev.s7a.bow.listener.EventListener
import dev.s7a.spigot.listener.registerListener
import org.bukkit.plugin.java.JavaPlugin

/**
 * プラグインのメインクラス
 */
@Suppress("unused")
class AcrobatSniper : JavaPlugin() {
    companion object {
        internal lateinit var plugin: JavaPlugin
    }

    init {
        plugin = this
    }

    override fun onEnable() {
        registerListener(EventListener)
        registerListener(DoubleJumpListener)
        registerListener(BowListener)
    }
}
