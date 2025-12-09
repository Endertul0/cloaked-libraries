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
    public CloakBlock(Settings settings, ConvertBlocks cBlock) {
        super(settings);

        setDefaultState(stateManager.getDefaultState().with(CONVERT_BLOCK, cBlock));
    }

    public static final EnumProperty<ConvertBlocks> CONVERT_BLOCK = EnumProperty.of("cblock", ConvertBlocks.class);

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CONVERT_BLOCK).build(Block::getDefaultState, BlockState::new);
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return ModBlocks.CLOAK.asItem().getDefaultStack();
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CloakBlockEntity(pos, state);
    }
}
