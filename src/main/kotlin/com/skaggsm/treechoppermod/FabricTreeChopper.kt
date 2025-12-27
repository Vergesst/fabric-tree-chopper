package com.skaggsm.treechoppermod

import com.skaggsm.treechoppermod.handler.ConfigRegistry
import com.skaggsm.treechoppermod.handler.ConfigRegistry.COMMON
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents
import net.minecraft.item.AxeItem
import net.minecraft.registry.Registries
import net.minecraft.util.ActionResult
import net.minecraft.util.Identifier

/**
 * Created by Mitchell Skaggs on 7/30/2019.
 */
class FabricTreeChopper : ModInitializer {
    companion object {
        const val MOD_ID = "fabric-tree-chopper"
    }

    override fun onInitialize() {
        ConfigRegistry.registerConfig()

        COMMON.registerSaveListener { manager, data ->
            return@registerSaveListener ActionResult.SUCCESS
        }

        PlayerBlockBreakEvents.AFTER.register(
            PlayerBlockBreakEvents.After { world, player, pos, state, _ ->
                tryLogBreak(world, player, pos, state)
            }
        )
    }
}
