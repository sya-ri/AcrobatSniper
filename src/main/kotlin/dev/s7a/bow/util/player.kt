package dev.s7a.bow.util

import org.bukkit.GameMode
import org.bukkit.entity.Player

/**
 * プレイヤーがアドベンチャーモードであるか
 */
var Player.isAdventureMode
    get() = gameMode == GameMode.ADVENTURE
    set(value) {
        if (value) {
            gameMode = GameMode.ADVENTURE
        }
    }
