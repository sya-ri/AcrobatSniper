package dev.s7a.bow

import dev.s7a.bow.listener.EventListener
import dev.s7a.spigot.listener.registerListener
import org.bukkit.plugin.java.JavaPlugin

/**
 * プラグインのメインクラス
 */
@Suppress("unused")
class AcrobatSniper : JavaPlugin() {
    override fun onEnable() {
        registerListener(EventListener)
    }
}
