package io.github.endertul.cloaked.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import io.github.endertul.cloaked.block.ModBlocks;
import io.github.endertul.cloaked.util.ModTags;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ModTags.Blocks.LOGS_KEY)
                .add(Blocks.OAK_LOG)
                .add(Blocks.SPRUCE_LOG)
                .add(Blocks.DARK_OAK_LOG)
                .add(Blocks.BIRCH_LOG)
                .add(Blocks.JUNGLE_LOG);
        getOrCreateTagBuilder(ModTags.Blocks.PLANKS_KEY)
                .add(Blocks.OAK_PLANKS)
                .add(Blocks.SPRUCE_PLANKS)
                .add(Blocks.DARK_OAK_PLANKS)
                .add(Blocks.BIRCH_PLANKS)
                .add(Blocks.JUNGLE_PLANKS);
        getOrCreateTagBuilder(ModTags.Blocks.OAK_KEY)
                .add(Blocks.OAK_LOG)
                .add(Blocks.OAK_PLANKS);
        getOrCreateTagBuilder(ModTags.Blocks.BIRCH_KEY)
                .add(Blocks.BIRCH_LOG)
                .add(Blocks.BIRCH_PLANKS);
        getOrCreateTagBuilder(ModTags.Blocks.SPRUCE_KEY)
                .add(Blocks.SPRUCE_LOG)
                .add(Blocks.SPRUCE_PLANKS);
        getOrCreateTagBuilder(ModTags.Blocks.DARK_OAK_KEY)
                .add(Blocks.DARK_OAK_LOG)
                .add(Blocks.DARK_OAK_PLANKS);
        getOrCreateTagBuilder(ModTags.Blocks.JUNGLE_KEY)
                .add(Blocks.JUNGLE_LOG)
                .add(Blocks.JUNGLE_PLANKS);

        //getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                //.add(ModBlocks.CLOAK);

        //getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                //.add(ModBlocks.RUBY_BLOCK);

        //getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                //.add(ModBlocks.RAW_RUBY_BLOCK)
                //.add(ModBlocks.RUBY_ORE);

        //getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                //.add(ModBlocks.DEEPSLATE_RUBY_ORE);

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("fabric", "needs_tool_level_4")))
                .add(ModBlocks.CLOAK);
    }
}