package com.blackout.chaosadditions.items;

import com.blackout.chaosadditions.util.CADEnumUtils;
import io.github.chaosawakens.api.item.IAutoEnchantable;
import io.github.chaosawakens.manager.CAConfigManager;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.function.Supplier;

public class EnchantedAIOTItem extends AIOTItem implements IAutoEnchantable {
	private final Supplier<EnchantmentData[]> enchantments;

	public EnchantedAIOTItem(CADEnumUtils.CADItemTier pTier, Supplier<ForgeConfigSpec.IntValue> configDmg, float pAttackSpeedModifier, Item.Properties properties, Supplier<EnchantmentData[]> enchantments) {
		super(pTier, configDmg, pAttackSpeedModifier, properties);
		this.enchantments = enchantments;
	}

	public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
		if (this.allowdedIn(group)) {
			ItemStack swordStack = new ItemStack(this);

			if (CAConfigManager.MAIN_COMMON.enableAutoEnchanting.get()) {
				for (EnchantmentData curEnch : enchantments.get()) {
					swordStack.enchant(curEnch.enchantment, curEnch.level);
				}
			}

			items.add(swordStack);
		}
	}

	public void onCraftedBy(ItemStack itemStack, World world, PlayerEntity playerEntity) {
		if (CAConfigManager.MAIN_COMMON.enableAutoEnchanting.get()) {
			for (EnchantmentData curEnch : enchantments.get()) {
				if (curEnch.level == 0) itemStack.enchant(curEnch.enchantment, curEnch.level);
			}
		}
	}

	public boolean isFoil(ItemStack stack) {
		return CAConfigManager.MAIN_COMMON.enableAutoEnchanting.get() && super.isFoil(stack) || super.isFoil(stack);
	}

	public EnchantmentData[] getEnchantments() {
		return this.enchantments.get();
	}
}
