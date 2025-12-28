package com.skaggsm.treechoppermod

import com.skaggsm.treechoppermod.handler.ConfigRegistry
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents

/**
 * Created by Mitchell Skaggs on 7/30/2019.
 */
class FabricTreeChopper : ModInitializer {
    companion object {
        const val MOD_ID = "fabric-tree-chopper"
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
