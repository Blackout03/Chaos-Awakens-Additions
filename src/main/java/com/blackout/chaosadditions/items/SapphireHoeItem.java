package com.blackout.chaosadditions.items;

import com.blackout.chaosadditions.util.CADEnumUtils;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import io.github.chaosawakens.api.item.ICATieredItem;
import io.github.chaosawakens.common.util.EntityUtil;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.util.Lazy;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class SapphireHoeItem extends HoeItem implements ICATieredItem {
	private Supplier<ForgeConfigSpec.IntValue> configDmg;
	private float attackSpeed;
	private double reach;
	private Lazy<? extends Multimap<Attribute, AttributeModifier>> attributeModMapLazy = Lazy.of(() -> {
		ImmutableMultimap.Builder<Attribute, AttributeModifier> attrModMapBuilder = ImmutableMultimap.builder();

		attrModMapBuilder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", getActualAttackDamage().get().get() - 1, AttributeModifier.Operation.ADDITION));
		attrModMapBuilder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", getAttackSpeed(), AttributeModifier.Operation.ADDITION));
		if (ForgeMod.REACH_DISTANCE.isPresent()) attrModMapBuilder.put(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(ICATieredItem.getReachUUIDMod(), "Weapon modifier", getReach(), AttributeModifier.Operation.ADDITION));

		return attrModMapBuilder.build();
	});
	public SapphireHoeItem(CADEnumUtils.CADItemTier pTier, Supplier<ForgeConfigSpec.IntValue> configDmg, float pAttackSpeedModifier, Item.Properties properties) {
		super(pTier, pTier.getAttackDamageMod(), pAttackSpeedModifier, properties);
		this.configDmg = configDmg;
		this.attackSpeed = pAttackSpeedModifier;
		this.reach = 0;
	}

	@Override
	public void inventoryTick(@Nonnull ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if (entity instanceof PlayerEntity && stack.getItem() == this) {
			PlayerEntity player = (PlayerEntity) entity;
			stack.getOrCreateTag().putBoolean("inWater", player.isUnderWater());
		}
	}

	@Override
	public float getDestroySpeed(@Nonnull ItemStack stack, BlockState state) {
		float defaultSpeed = super.getDestroySpeed(stack, state);
		boolean isInWater = stack.hasTag() && stack.getTag() != null && stack.getTag().getBoolean("inWater");
		return isInWater ? (defaultSpeed * 3.0F) * 3.0F : defaultSpeed;
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
		return slot.equals(EquipmentSlotType.MAINHAND) ? attributeModMapLazy.get() : super.getAttributeModifiers(slot, stack);
	}

	@Override
	public Supplier<ForgeConfigSpec.IntValue> getActualAttackDamage() {
		return configDmg;
	}

	@Override
	public void setAttackDamage(Supplier<ForgeConfigSpec.IntValue> attackDamage) {
		this.configDmg = attackDamage;
	}

	@Override
	public float getAttackSpeed() {
		return attackSpeed;
	}

	@Override
	public void setAttackSpeed(float attackSpeed) {
		this.attackSpeed = attackSpeed - 2.4F;
	}

	@Override
	public double getReach() {
		return reach;
	}

	@Override
	public void setReach(double reach) {
		this.reach = reach;
	}

	@Override
	public double getAttackKnockback() {
		return 0;
	}

	@Override
	public void setAttackKnockback(double attackKnockback) {

	}

	@Override
	public void setAttributeModifiers(Lazy<? extends Multimap<Attribute, AttributeModifier>> attributeModMapLazy) {
		this.attributeModMapLazy = attributeModMapLazy;
	}

	@Override
	public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
		EntityUtil.applyReachModifierToEntity(entity, stack, (float) this.getActualAttackDamage().get().get());
		return super.onEntitySwing(stack, entity);
	}
}
