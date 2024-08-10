package com.blackout.chaosadditions.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class CADCommonConfig {
	public static final ForgeConfigSpec COMMON_SPEC;
	public static final Common COMMON;

	static {
		final Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = commonSpecPair.getRight();
		COMMON = commonSpecPair.getLeft();
	}

	public static class Common {
		public final ForgeConfigSpec.BooleanValue enableSapphireShipwreckLootGen;
		public final ForgeConfigSpec.BooleanValue enableOreSapphireGen;
		public final ForgeConfigSpec.BooleanValue enableSapphireArmorSetBonus;

		Common(ForgeConfigSpec.Builder builder) {
			builder.push("Functionality");
			builder.push("Armor");
			builder.push("Sapphire Armor");
			enableSapphireArmorSetBonus = builder.define("Enable the Sapphire set bonus", true);
			builder.pop();
			builder.pop();
			builder.pop();
			builder.push("World Generation");
			enableSapphireShipwreckLootGen = builder.define("Sapphire shipwreck loot generation", true);
			builder.push("Specific Ore Spawning");
			this.enableOreSapphireGen = builder.define("Sapphire ore generation", true);
			builder.pop();
			builder.pop();
		}
	}
}
