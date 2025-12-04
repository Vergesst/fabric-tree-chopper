package com.skaggsm.treechoppermod.mixin;

import com.skaggsm.treechoppermod.handler.ConfigRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.spongepowered.asm.mixin.injection.callback.LocalCapture.CAPTURE_FAILSOFT;

@Pseudo
// @Mixin(targets = {LeavesBlock.class, "com.epherical.croptopia.blocks.LeafCropBlock"})
// croptopis --- external mods
@Mixin(value = {LeavesBlock.class})
public abstract class LeavesBlockMixin extends Block {
    public LeavesBlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(at = @At("TAIL"), method = {"scheduledTick"}, remap = false)
    private void onScheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        var newState = world.getBlockState(pos);

        var distance = newState.get(Properties.DISTANCE_1_7);
        var persistent = newState.getOrEmpty(Properties.PERSISTENT).orElse(false);

        if (distance == 7 && !persistent && ConfigRegistry.COMMON.get().getLeafConfig().getFastLeafDecay())
            randomTick(state, world, pos, random);
    }
}
