package com.skaggsm.treechoppermod.core

import com.skaggsm.treechoppermod.FabricTreeChopper
import me.shedaniel.autoconfig.ConfigData
import me.shedaniel.autoconfig.annotation.Config
import me.shedaniel.autoconfig.annotation.ConfigEntry

@Config(name = FabricTreeChopper.MOD_ID)
class ChopperConfig: ConfigData {
    // Leaf config
    @ConfigEntry.Category(value = "leaf")
    @ConfigEntry.Gui.TransitiveObject
    var leafConfig = LeafConfig()

    var stopBeforeAxeBreak = true
    var chopInCreativeMode = false
    var variableBreakingTime = false

    // Enums
    @ConfigEntry.Gui.EnumHandler
    var chopMode = ChopMode.SINGLE_CHOP

    @ConfigEntry.Gui.EnumHandler
    var durabilityMode = DurabilityMode.BREAK_MID_CHOP

    @ConfigEntry.Gui.EnumHandler
    var sneakBehavior = SneakBehavior.DISABLED

    var logSearchLimit = 1000

    class LeafConfig: ConfigData {
        var fastLeafDecay = true
        var requireLeafToChop = true
    }
}