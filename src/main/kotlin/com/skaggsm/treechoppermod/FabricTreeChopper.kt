package com.skaggsm.treechoppermod

import com.skaggsm.treechoppermod.handler.ConfigRegistry
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents
import net.minecraft.item.AxeItem
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier

/**
 * Created by Mitchell Skaggs on 7/30/2019.
 */
class FabricTreeChopper : ModInitializer {
    companion object {
        const val MOD_ID = "fabric-tree-chopper"

        val axes: MutableList<Identifier> = Registries.ITEM.stream()
            .filter(AxeItem::class.java::isInstance)
            .map(Registries.ITEM::getId)
            .toList()
    }

    override fun onInitialize() {
        ConfigRegistry.registerConfig()

        PlayerBlockBreakEvents.AFTER.register(
            PlayerBlockBreakEvents.After { world, player, pos, state, _ ->
                tryLogBreak(world, player, pos, state)
            }
        )
    }
}
