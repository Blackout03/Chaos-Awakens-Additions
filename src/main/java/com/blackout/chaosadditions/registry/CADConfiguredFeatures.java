package com.blackout.chaosadditions.registry;

import io.github.chaosawakens.api.wrapper.FeatureWrapper;
import io.github.chaosawakens.common.events.CACommonSetupEvents;
import io.github.chaosawakens.common.worldgen.placement.OceanBedPlacement;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.DepthAverageConfig;

public class CADConfiguredFeatures {
	public static final ConfiguredFeature<?, ?> ORE_SAPPHIRE = register("ore_sapphire", Feature.ORE.configured(new OreFeatureConfig(RuleTests.BASE_OCEAN_FLOOR, States.SAPPHIRE_ORE, 4)).decorated(OceanBedPlacement.DEPTH_AVERAGE.configured(new DepthAverageConfig(30, 15))).squared().count(5));

	private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
		CACommonSetupEvents.CONFIG_FEATURES.add(new FeatureWrapper(key, configuredFeature));
		return configuredFeature;
	}

	public static final class States {
		private static final BlockState SAPPHIRE_ORE = CADBlocks.SAPPHIRE_ORE.get().defaultBlockState();
	}

	public static final class RuleTests {
		public static final RuleTest BASE_OCEAN_FLOOR = new BlockMatchRuleTest(Blocks.GRAVEL);
	}
}
