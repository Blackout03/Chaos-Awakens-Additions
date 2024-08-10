package com.blackout.chaosadditions.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class CADServerConfig {
	public static final ForgeConfigSpec SERVER_SPEC;
	public static final Server SERVER;

	static {
		final Pair<Server, ForgeConfigSpec> serverSpecPair = new ForgeConfigSpec.Builder().configure(Server::new);
		SERVER_SPEC = serverSpecPair.getRight();
		SERVER = serverSpecPair.getLeft();
	}

	public static class Server {
		public final ForgeConfigSpec.IntValue woodenAIOTDamage;
		public final ForgeConfigSpec.IntValue stoneAIOTDamage;
		public final ForgeConfigSpec.IntValue goldenAIOTDamage;
		public final ForgeConfigSpec.IntValue ironAIOTDamage;
		public final ForgeConfigSpec.IntValue diamondAIOTDamage;
		public final ForgeConfigSpec.IntValue netheriteAIOTDamage;
		public final ForgeConfigSpec.IntValue crystalWoodAIOTDamage;
		public final ForgeConfigSpec.IntValue kyaniteAIOTDamage;
		public final ForgeConfigSpec.IntValue pinkTourmalineAIOTDamage;
		public final ForgeConfigSpec.IntValue catsEyeAIOTDamage;
		public final ForgeConfigSpec.IntValue copperAIOTDamage;
		public final ForgeConfigSpec.IntValue tinAIOTDamage;
		public final ForgeConfigSpec.IntValue silverAIOTDamage;
		public final ForgeConfigSpec.IntValue platinumAIOTDamage;
		public final ForgeConfigSpec.IntValue emeraldAIOTDamage;
		public final ForgeConfigSpec.IntValue amethystAIOTDamage;
		public final ForgeConfigSpec.IntValue tigersEyeAIOTDamage;
		public final ForgeConfigSpec.IntValue rubyAIOTDamage;
		public final ForgeConfigSpec.IntValue ultimateAIOTDamage;
		public final ForgeConfigSpec.IntValue sapphireAIOTDamage;
		public final ForgeConfigSpec.IntValue meganiumAIOTDamage;

		public final ForgeConfigSpec.IntValue sapphireSwordDamage;
		public final ForgeConfigSpec.IntValue sapphirePickaxeDamage;
		public final ForgeConfigSpec.IntValue sapphireAxeDamage;
		public final ForgeConfigSpec.IntValue sapphireShovelDamage;
		public final ForgeConfigSpec.IntValue sapphireHoeDamage;

		Server(ForgeConfigSpec.Builder builder) {
			builder.push("Attack Damage");
			builder.push("AIOT");
			woodenAIOTDamage = builder.defineInRange("Damage of the Wooden AIOT", 3, 0, Integer.MAX_VALUE);
			stoneAIOTDamage = builder.defineInRange("Damage of the Stone AIOT", 4, 0, Integer.MAX_VALUE);
			goldenAIOTDamage = builder.defineInRange("Damage of the Golden AIOT", 3, 0, Integer.MAX_VALUE);
			ironAIOTDamage = builder.defineInRange("Damage of the Iron AIOT", 5, 0, Integer.MAX_VALUE);
			diamondAIOTDamage = builder.defineInRange("Damage of the Diamond AIOT", 6, 0, Integer.MAX_VALUE);
			netheriteAIOTDamage = builder.defineInRange("Damage of the Netherite AIOT", 7, 0, Integer.MAX_VALUE);
			crystalWoodAIOTDamage = builder.defineInRange("Damage of the Crystal Wood AIOT", 3, 0, Integer.MAX_VALUE);
			kyaniteAIOTDamage = builder.defineInRange("Damage of the Kyanite AIOT", 4, 0, Integer.MAX_VALUE);
			pinkTourmalineAIOTDamage = builder.defineInRange("Damage of the Pink Tourmaline AIOT", 10, 0, Integer.MAX_VALUE);
			catsEyeAIOTDamage = builder.defineInRange("Damage of the Cat's Eye AIOT", 11, 0, Integer.MAX_VALUE);
			copperAIOTDamage = builder.defineInRange("Damage of the Copper AIOT", 4, 0, Integer.MAX_VALUE);
			tinAIOTDamage = builder.defineInRange("Damage of the Tin AIOT", 4, 0, Integer.MAX_VALUE);
			silverAIOTDamage = builder.defineInRange("Damage of the Silver AIOT", 5, 0, Integer.MAX_VALUE);
			platinumAIOTDamage = builder.defineInRange("Damage of the Platinum AIOT", 9, 0, Integer.MAX_VALUE);
			emeraldAIOTDamage = builder.defineInRange("Damage of the Emerald AIOT", 5, 0, Integer.MAX_VALUE);
			amethystAIOTDamage = builder.defineInRange("Damage of the Amethyst AIOT", 14, 0, Integer.MAX_VALUE);
			tigersEyeAIOTDamage = builder.defineInRange("Damage of the Tiger's Eye AIOT", 11, 0, Integer.MAX_VALUE);
			rubyAIOTDamage = builder.defineInRange("Damage of the Ruby AIOT", 19, 0, Integer.MAX_VALUE);
			ultimateAIOTDamage = builder.defineInRange("Damage of the Ultimate AIOT", 39, 0, Integer.MAX_VALUE);
			sapphireAIOTDamage = builder.defineInRange("Damage of the Sapphire AIOT", 11, 0, Integer.MAX_VALUE);
			meganiumAIOTDamage = builder.defineInRange("Damage of the Meganium AIOT", 59, 0, Integer.MAX_VALUE);
			builder.pop();
			builder.push("Sapphire Weapons/Tools");
			sapphireSwordDamage = builder.defineInRange("Damage of the Sapphire Sword", 12, 0, Integer.MAX_VALUE);
			sapphireAxeDamage = builder.defineInRange("Damage of the Sapphire Axe", 14, 0, Integer.MAX_VALUE);
			sapphirePickaxeDamage = builder.defineInRange("Damage of the Sapphire Pickaxe", 10, 0, Integer.MAX_VALUE);
			sapphireShovelDamage = builder.defineInRange("Damage of the Sapphire Shovel", 11, 0, Integer.MAX_VALUE);
			sapphireHoeDamage = builder.defineInRange("Damage of the Sapphire Hoe", 1, 0, Integer.MAX_VALUE);
			builder.pop();
			builder.pop();
		}
	}
}
