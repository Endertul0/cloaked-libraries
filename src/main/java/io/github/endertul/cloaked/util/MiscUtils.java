package io.github.endertul.cloaked.util;

import io.github.endertul.cloaked.CloakedLibraries;
import io.github.endertul.cloaked.block.ModBlocks;
import io.github.endertul.cloaked.block.custom.CloakBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MiscUtils {
    public static List<BlockPos> getNeighbors(BlockPos pos) {
        List<BlockPos> positions = new ArrayList<BlockPos>();
        positions.add(pos.up().east().north());
        positions.add(pos.up().east().south());
        positions.add(pos.up().west().north());
        positions.add(pos.up().west().south());
        positions.add(pos.up().west());
        positions.add(pos.up().east());
        positions.add(pos.up().south());
        positions.add(pos.up().north());
        positions.add(pos.up());
        positions.add(pos.down().east().north());
        positions.add(pos.down().east().south());
        positions.add(pos.down().west().north());
        positions.add(pos.down().west().south());
        positions.add(pos.down().west());
        positions.add(pos.down().east());
        positions.add(pos.down().south());
        positions.add(pos.down().north());
        positions.add(pos.down());
        positions.add(pos.east().north());
        positions.add(pos.east().south());
        positions.add(pos.west().north());
        positions.add(pos.west().south());
        positions.add(pos.west());
        positions.add(pos.east());
        positions.add(pos.south());
        positions.add(pos.north());
        return positions;
    }

    public static void printEnum() {
        for (Block block : Registries.BLOCK) {
            String bString = block.toString();
            Identifier id = Registries.BLOCK.getId(block);
            CloakedLibraries.LOGGER.info(bString.toUpperCase(Locale.ROOT) + "(Blocks." + id.toString().toUpperCase() + ", Blocks." + id.toString().toUpperCase() + ".getDefaultState()),");
        }
    }

    private static ActionResult cloak(ItemUsageContext context, boolean expand, BlockPos pos, int currentTally) {
        World world = context.getWorld();
        // BlockState before conversion
        BlockState blockState = world.getBlockState(pos);

        NbtCompound blockEntityNBT;
        if (blockState.hasBlockEntity()) {
            BlockEntity blEnt = world.getBlockEntity(pos);
            blockEntityNBT = blEnt.createNbtWithIdentifyingData();

            // Remove inventory, after reading it into the NBT
            NbtCompound blockEntityNBTCopy = blockEntityNBT.copy();
            blockEntityNBTCopy.remove("Items");
            blEnt.readNbt(blockEntityNBTCopy);
        } else {
            blockEntityNBT = null;
        }


        world.setBlockState(pos, ModBlocks.CLOAK.getDefaultState());
        CloakBlockEntity cloakBlockEntity = (CloakBlockEntity) world.getBlockEntity(pos);

        // Set storedBlockState
        assert cloakBlockEntity != null;
        cloakBlockEntity.setStoredBlockState(blockState);

        // Set storedNbt
        if (blockEntityNBT != null) {
            cloakBlockEntity.setStoredNbt(blockEntityNBT);
        }

        // Handle expansion
        if (expand) {
            if (currentTally > 256) {
                return ActionResult.FAIL;
            }
            for (BlockPos listPos : getNeighbors(pos)) {
                if (! world.getBlockState(listPos).isOf(Blocks.AIR)) {
                    if (! world.getBlockState(listPos).isOf(ModBlocks.CLOAK)) {
                        currentTally++;
                        cloak(context, true, listPos, currentTally);
                    }
                }
            }
        }

        return ActionResult.SUCCESS;
    }

    public static ActionResult cloak(ItemUsageContext context, boolean expand, BlockPos pos) {
        return cloak(context, expand, pos, 0);
    }

    public static ActionResult decloak(WorldAccess world, BlockPos pos, BlockState state, int currentTally) {
        if (currentTally > 256) {
            return ActionResult.FAIL;
        }
        CloakBlockEntity blockEntity = (CloakBlockEntity) world.getBlockEntity(pos);
        BlockState decodedState = blockEntity.getStoredBlockState();
        NbtCompound storedNbt = blockEntity.getStoredNbt();

        world.setBlockState(pos, decodedState, Block.NOTIFY_ALL);
        if (decodedState.hasBlockEntity()) {
            world.getBlockEntity(pos).readNbt(storedNbt);
        }

        List<BlockPos> positions = MiscUtils.getNeighbors(pos);
        for (BlockPos listPos : positions) {
            BlockState listBlockState = world.getBlockState(listPos);
            Block listBlock = listBlockState.getBlock();
            if (listBlock == ModBlocks.CLOAK) {
                currentTally++;
                decloak(world, listPos, listBlockState, currentTally);
            }
        }

        return ActionResult.SUCCESS;
    }

    public static ActionResult decloak(WorldAccess world, BlockPos pos, BlockState state) {
        return decloak(world, pos, state, 0);
    }
}