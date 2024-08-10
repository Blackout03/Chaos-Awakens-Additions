package com.blackout.chaosadditions.data;

import com.blackout.chaosadditions.ChaosAdditions;
import com.blackout.chaosadditions.config.CADCommonConfig;
import com.blackout.chaosadditions.loot.CADLootModifier;
import com.blackout.chaosadditions.registry.CADGlobalLootModifier;
import com.blackout.chaosadditions.registry.CADItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class CADLootProvider extends GlobalLootModifierProvider {
	public CADLootProvider(DataGenerator gen) {
		super(gen, ChaosAdditions.MODID);
	}

	@Override
	protected void start() {
		if (CADCommonConfig.COMMON.enableSapphireShipwreckLootGen.get()) {
			add("sapphire_treasure", CADGlobalLootModifier.SAPPHIRE_TREASURE.get(), new CADLootModifier(
					new ILootCondition[]{
							LootTableIdCondition.builder(new ResourceLocation("chests/shipwreck_treasure")).build()
					}, 0.125f, 1, 1, CADItems.SAPPHIRE.get()
			));
		}
	}
}
