package com.skaggsm.treechoppermod.core

// core files
enum class ChopMode {
    FULL_CHOP,
    SINGLE_CHOP,
    VANILLA_CHOP,
}

/**
 * Defines durability behavior specifically for the FULL_CHOP mode.
 * Placing it here makes that relationship clearer.
 */
enum class DurabilityMode {
    NO_DURABILITY_LOSS,
    BREAK_AFTER_CHOP,
    BREAK_MID_CHOP,
}

/**
 * Defines how sneaking affects the chopping action.
 */
enum class SneakBehavior {
    DISABLED,
    ENABLED,
    ALWAYS_CHOP;

    fun shouldChop(isSneaking: Boolean): Boolean {
        return when (this) {
            DISABLED -> !isSneaking
            ENABLED -> isSneaking
            ALWAYS_CHOP -> true
        }
    }
}
