package dev.s7a.bow.util

import dev.s7a.spigot.util.itemStack
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

/**
 * 配布する弓
 */
val Bow: ItemStack
    get() = itemStack(Material.BOW, displayName = "&d弓") {
        addEnchant(Enchantment.ARROW_DAMAGE, 5, true)
        addEnchant(Enchantment.ARROW_INFINITE, 1, true)
        isUnbreakable = true
        addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
    }
