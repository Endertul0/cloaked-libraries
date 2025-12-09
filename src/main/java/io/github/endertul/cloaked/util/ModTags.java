package io.github.endertul.cloaked.util;

import io.github.endertul.cloaked.CloakedLibraries;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> LOGS_KEY = createTag("logs_key");
        public static final TagKey<Block> PLANKS_KEY = createTag("planks_key");

        public static final TagKey<Block> OAK_KEY = createTag("oak_key");
        public static final TagKey<Block> BIRCH_KEY = createTag("birch_key");
        public static final TagKey<Block> SPRUCE_KEY = createTag("spruce_key");
        public static final TagKey<Block> DARK_OAK_KEY = createTag("dark_oak_key");
        public static final TagKey<Block> JUNGLE_KEY = createTag("jungle_key");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(CloakedLibraries.MOD_ID, name));
        }
    }

    public static class Items {


        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(io.github.endertul.cloaked.CloakedLibraries.MOD_ID, name));
        }
    }
}
