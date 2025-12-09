package io.github.endertul.cloaked;

import io.github.endertul.cloaked.block.ModBlockEntities;
import io.github.endertul.cloaked.block.ModBlocks;
import io.github.endertul.cloaked.item.ModItemGroups;
import io.github.endertul.cloaked.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CloakedLibraries implements ModInitializer {
	public static final String MOD_ID = "cloaked";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initialized " + MOD_ID);

		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerModBlockEntities();
	}
}