package com.blackout.chaosadditions.events;

import com.blackout.chaosadditions.config.CADCommonConfig;
import com.blackout.chaosadditions.registry.CADConfiguredFeatures;
import io.github.chaosawakens.manager.CAConfigManager;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class BiomeLoadEventSubscriber {
	public static void onBiomeLoadingEvent(final BiomeLoadingEvent event) {
		StructureHandler.addFeatures(event);
	}

	private static class StructureHandler {
		public static void addFeatures(BiomeLoadingEvent event) {
			BiomeGenerationSettingsBuilder gen = event.getGeneration();

			RegistryKey<Biome> biome = RegistryKey.create(ForgeRegistries.Keys.BIOMES, Objects.requireNonNull(event.getName(), "Who registered null name biome, naming criticism!"));

			if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN)) {
				if (CAConfigManager.MAIN_COMMON.enableOreGen.get())
					addOceanOres(gen);
			}
		}

		private static void addOceanOres(BiomeGenerationSettingsBuilder gen) {
			if (CADCommonConfig.COMMON.enableOreSapphireGen.get()) gen.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, CADConfiguredFeatures.ORE_SAPPHIRE);
		}
	}
}
