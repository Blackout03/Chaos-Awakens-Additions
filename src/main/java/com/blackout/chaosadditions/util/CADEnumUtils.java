package com.blackout.chaosadditions.util;

import com.blackout.chaosadditions.ChaosAdditions;
import com.blackout.chaosadditions.registry.CADItems;
import io.github.chaosawakens.common.registry.CABlocks;
import io.github.chaosawakens.common.registry.CAItems;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

import java.util.function.Supplier;

public class CADEnumUtils {
	public enum CADItemTier implements IItemTier {
		// Harvest level, Max uses, Efficiency, Damage, Enchantability
		TOOL_SAPPHIRE(4, 2200, 9, 14, 20, () -> Ingredient.of(CADItems.SAPPHIRE.get())),
		TOOL_MEGANIUM(7, 7200, 30, 46, 64, () -> Ingredient.EMPTY),
		TOOL_EMERALD(3, 1300, 8.0F, 6.0F, 24, () -> Ingredient.of(Items.EMERALD.getItem())),
		TOOL_EXPERIENCE(3, 3000, 8.0F, 6.0F, 30, () -> Ingredient.of(Items.EMERALD.getItem(), Items.EXPERIENCE_BOTTLE)),
		TOOL_AMETHYST(4, 2700, 9.0F, 11.0F, 18, () -> Ingredient.of( CAItems.AMETHYST.get())),
		TOOL_RUBY(5, 3000, 10.0F, 16.0F, 22, () -> Ingredient.of(CAItems.RUBY.get())),
		TOOL_TIGERS_EYE(4, 2400, 10.0F, 8.0F, 20, () -> Ingredient.of(CAItems.TIGERS_EYE.get())),
		TOOL_CRYSTALWOOD(0, 300, 2.0F, 1.0F, 6, () -> Ingredient.of(CABlocks.CRYSTALWOOD_PLANKS.get())),
		TOOL_KYANITE(1, 800, 3.0F, 2.0F, 6, () -> Ingredient.of(CABlocks.KYANITE.get())),
		TOOL_PINK_TOURMALINE(2, 1100, 7.0F, 8.0F, 6, () -> Ingredient.of(CAItems.PINK_TOURMALINE_INGOT.get())),
		TOOL_CATS_EYE(3, 1600, 8.0F, 8.0F, 20, () -> Ingredient.of(CAItems.CATS_EYE_INGOT.get())),
		TOOL_ULTIMATE(6, 6000, 25.0F, 36.0F, 64, () -> Ingredient.of(CAItems.TITANIUM_INGOT.get(), CAItems.URANIUM_INGOT.get())),
		TOOL_COPPER(2, 150, 4.0F, 2.0F, 6, () -> Ingredient.of(CAItems.COPPER_LUMP.get())),
		TOOL_TIN(2, 180, 5.0F, 3.0F, 8, () -> Ingredient.of(CAItems.TIN_LUMP.get())),
		TOOL_SILVER(3, 450, 7.0F, 4.0F, 10, () -> Ingredient.of(CAItems.SILVER_LUMP.get())),
		TOOL_PLATINUM(5, 1600, 8.0F, 6.0F, 12, () -> Ingredient.of(CAItems.PLATINUM_LUMP.get())),
		WOOD(0, 59, 2.0F, 0.0F, 15, () -> Ingredient.of(ItemTags.PLANKS)),
		STONE(1, 131, 4.0F, 1.0F, 5, () -> Ingredient.of(ItemTags.STONE_TOOL_MATERIALS)),
		IRON(2, 250, 6.0F, 2.0F, 14, () -> Ingredient.of(Items.IRON_INGOT)),
		DIAMOND(3, 1561, 8.0F, 3.0F, 10, () -> Ingredient.of(Items.DIAMOND)),
		GOLD(0, 32, 12.0F, 0.0F, 22, () -> Ingredient.of(Items.GOLD_INGOT)),
		NETHERITE(4, 2031, 9.0F, 4.0F, 15, () -> Ingredient.of(Items.NETHERITE_INGOT));


		private final int harvestLevel;
		private final int maxUses;
		private final float efficiency;
		private final float attackDamage;
		private final int enchantability;
		private final Supplier<Ingredient> repairMaterial;

		CADItemTier(int harvestLevel, int maxUses, float efficiency, float damage, int enchantability, Supplier<Ingredient> repairMaterial) {
			this.harvestLevel = harvestLevel;
			this.maxUses = maxUses;
			this.efficiency = efficiency;
			this.attackDamage = damage;
			this.enchantability = enchantability;
			this.repairMaterial = repairMaterial;
		}

		@Override
		public int getUses() {
			return this.maxUses;
		}

		@Override
		public float getSpeed() {
			return this.efficiency;
		}

		@Override
		public float getAttackDamageBonus() {
			return this.attackDamage;
		}

		@Override
		public int getLevel() {
			return this.harvestLevel;
		}

		@Override
		public int getEnchantmentValue() {
			return this.enchantability;
		}

		@Override
		public Ingredient getRepairIngredient() {
			return this.repairMaterial.get();
		}

		// Copy from Chaos Awakens
		public int getAttackDamageMod() {
			return (int) (getAttackDamageBonus() - (getAttackDamageBonus() + 1)) - 2;
		}
	}

	public enum CADArmorMaterial implements IArmorMaterial {
		//Name, Durability multiplier, Damage Reduction multiplier, Damage Reduction, Enchantability, Sound Events, Toughness, Knockback Resistance, Repair Material
		SAPPHIRE("sapphire", 34, new int[]{3, 7, 8, 3}, 20, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5f, 0f, () -> Ingredient.of(CADItems.SAPPHIRE.get()));

		private final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
		private final String name;
		private final int durability;
		private final int[] damageReductionAmountArray;
		private final int enchantability;
		private final SoundEvent soundOnEquip;
		private final float toughness;
		private final float knockbackResistance;
		private final Supplier<Ingredient> repairMaterial;

		CADArmorMaterial(String nameIn, int durabilityIn, int[] damageReductionAmountArrayIn, int enchantabilityIn, SoundEvent soundOnEquip, float toughnessIn, float knockbackResistanceIn, Supplier<Ingredient> repairMaterialIn) {
			this.name = ChaosAdditions.MODID + ":" + nameIn;
			this.durability = durabilityIn;
			this.damageReductionAmountArray = damageReductionAmountArrayIn;
			this.enchantability = enchantabilityIn;
			this.soundOnEquip = soundOnEquip;
			this.toughness = toughnessIn;
			this.knockbackResistance = knockbackResistanceIn;
			this.repairMaterial = repairMaterialIn;
		}

		@Override
		public int getDurabilityForSlot(EquipmentSlotType p_200896_1_) {
			return MAX_DAMAGE_ARRAY[p_200896_1_.getIndex()] * this.durability;
		}

		@Override
		public int getDefenseForSlot(EquipmentSlotType p_200902_1_) {
			return this.damageReductionAmountArray[p_200902_1_.getIndex()];
		}

		@Override
		public int getEnchantmentValue() {
			return this.enchantability;
		}

		@Override
		public SoundEvent getEquipSound() {
			return this.soundOnEquip;
		}

		@Override
		public Ingredient getRepairIngredient() {
			return this.repairMaterial.get();
		}

		@Override
		public String getName() {
			return this.name;
		}

		@Override
		public float getToughness() {
			return this.toughness;
		}

		@Override
		public float getKnockbackResistance() {
			return this.knockbackResistance;
		}
	}
}
