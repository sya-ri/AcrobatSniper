package dev.s7a.bow.util

import org.bukkit.GameMode
import org.bukkit.entity.Player

/**
 * プレイヤーがクリエイティブモードであるか
 */
var Player.isCreativeMode
    get() = gameMode == GameMode.CREATIVE
    set(value) {
        if (value) {
            gameMode = GameMode.CREATIVE
        }
    }

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
