package com.blackout.chaosadditions.items;

import com.blackout.chaosadditions.config.CADCommonConfig;
import com.blackout.chaosadditions.registry.CADItems;
import io.github.chaosawakens.api.IUtilityHelper;
import io.github.chaosawakens.manager.CAConfigManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class SapphireArmorItem extends ArmorItem {
	public SapphireArmorItem(IArmorMaterial armorMaterial, EquipmentSlotType equipmentSlot, Properties properties) {
		super(armorMaterial, equipmentSlot, properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		if (!CAConfigManager.MAIN_CLIENT.enableTooltips.get()) return;
		super.appendHoverText(stack, world, tooltip, flag);
		tooltip.add(new StringTextComponent("Full Set Bonus: ").withStyle(TextFormatting.GOLD).append(new StringTextComponent("Neptune's Favor").withStyle(TextFormatting.DARK_GREEN)).append(new StringTextComponent(" (...)").withStyle(TextFormatting.GREEN)));

		if (Screen.hasShiftDown() || Screen.hasControlDown()) {
			tooltip.removeIf((s) -> s.toString().contains("(...)"));
			tooltip.add(new StringTextComponent("Full Set Bonus: ").withStyle(TextFormatting.GOLD).append(new StringTextComponent("Neptune's Favor").withStyle(TextFormatting.DARK_GREEN))
					.append(new StringTextComponent("\nUnderwater, you breathe freely and move with the elegance and speed of the ocean's guardians.").withStyle(TextFormatting.GREEN)));
		}

		if (!CADCommonConfig.COMMON.enableSapphireArmorSetBonus.get()) {
			tooltip.add(new StringTextComponent("This full set bonus is disabled in the config!").withStyle(TextFormatting.RED).withStyle(TextFormatting.BOLD));
		}
	}

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		if (world.isClientSide) return;
		if (CADCommonConfig.COMMON.enableSapphireArmorSetBonus.get()) {
			if (player.isUnderWater()) {
				if (player.getArmorSlots() != null) {
					if (IUtilityHelper.isFullArmorSet(player, CADItems.SAPPHIRE_HELMET.get(), CADItems.SAPPHIRE_CHESTPLATE.get(), CADItems.SAPPHIRE_LEGGINGS.get(), CADItems.SAPPHIRE_BOOTS.get())) {
						player.addEffect(new EffectInstance(Effects.WATER_BREATHING, 100, 0, false, false, false));
						player.addEffect(new EffectInstance(Effects.DOLPHINS_GRACE, 100, 0, false, false, false));
					}
				}
			}
		}
	}
}
