package com.skaggsm.treechoppermod.handler

import com.skaggsm.treechoppermod.core.ChopperConfig
import me.shedaniel.autoconfig.AutoConfig
import me.shedaniel.autoconfig.ConfigHolder
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer

object ConfigRegistry {
    lateinit var COMMON: ConfigHolder<ChopperConfig>

    fun registerConfig() {
        COMMON = AutoConfig.register(
            ChopperConfig::class.java,
            ::GsonConfigSerializer
        )
    }
}