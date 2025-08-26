package com.skaggsm.treechoppermod.mixin;

import com.skaggsm.treechoppermod.LogBlockUtilsKt;
import com.skaggsm.treechoppermod.handler.ConfigRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PillarBlock.class)
public abstract class PillarBlockMixin extends Block {
    PillarBlockMixin(Settings settings) {
        super(settings);
    }

    @SuppressWarnings("deprecation")
    @Override
    public float calcBlockBreakingDelta(BlockState state, PlayerEntity player, BlockView world, BlockPos pos) {
        float delta = super.calcBlockBreakingDelta(state, player, world, pos);
        if (ConfigRegistry.COMMON.get().getVariableBreakingTime() && LogBlockUtilsKt.canBreakLog(player, state)) {
            var numLogs = LogBlockUtilsKt.findAllLogsAbove(state, player.getWorld(), pos).size();
            delta /= (float) Math.pow(numLogs + 1, 0.75);
        }
        return delta;
    }
}
