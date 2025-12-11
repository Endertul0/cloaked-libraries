package io.github.endertul.cloaked.block.custom;

import io.github.endertul.cloaked.block.ModBlocks;
import io.github.endertul.cloaked.util.ConvertBlocks;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class CloakBlock extends BlockWithEntity {
    public CloakBlock(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CloakBlockEntity(pos, state);
    }
}
