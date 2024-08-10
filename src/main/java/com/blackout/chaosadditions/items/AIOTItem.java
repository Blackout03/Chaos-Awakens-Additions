package com.blackout.chaosadditions.items;

import com.blackout.chaosadditions.util.CADEnumUtils;
import com.blackout.chaosadditions.util.ToolUtil;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import io.github.chaosawakens.api.item.ICATieredItem;
import io.github.chaosawakens.common.util.EntityUtil;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.util.Lazy;

import java.util.List;
import java.util.function.Supplier;

public class AIOTItem extends ToolItem implements ICATieredItem {
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

	public AIOTItem(CADEnumUtils.CADItemTier pTier, Supplier<ForgeConfigSpec.IntValue> configDmg, float pAttackSpeedModifier, Item.Properties properties) {
		super(pTier.getAttackDamageMod(), pAttackSpeedModifier, pTier, null, properties
				.addToolType(ToolType.PICKAXE, pTier.getLevel())
				.addToolType(ToolType.AXE, pTier.getLevel())
				.addToolType(ToolType.SHOVEL, pTier.getLevel())
				.addToolType(ToolType.HOE, pTier.getLevel()));
		this.configDmg = configDmg;
		this.attackSpeed = pAttackSpeedModifier;
		this.reach = 0;
	}

	public boolean isCorrectToolForDrops(BlockState state) {
		return !(state.getHarvestTool() == ToolType.AXE) || !(state.getHarvestTool() == ToolType.HOE) || !(state.getHarvestTool() == ToolType.SHOVEL) || !(state.getHarvestTool() == ToolType.PICKAXE);
	}

	public float getDestroySpeed(ItemStack itemStack, BlockState blockState) {
		return this.speed;
	}

	public ActionResultType useOn(ItemUseContext itemUseContext) {
		PlayerEntity player = itemUseContext.getPlayer();
		BlockPos pos = itemUseContext.getClickedPos();
		World world = itemUseContext.getLevel();

		if (player == null) return ActionResultType.PASS;
		if (player.isShiftKeyDown() && world.getBlockState(pos).getBlock().isToolEffective(world.getBlockState(pos), ToolType.SHOVEL)) return ToolUtil.hoeUse(itemUseContext);
		else if (player.isShiftKeyDown() && world.getBlockState(pos).getBlock().isToolEffective(world.getBlockState(pos), ToolType.AXE)) return ToolUtil.axeUse(itemUseContext);
		else if (!player.isShiftKeyDown()) return ToolUtil.shovelUse(itemUseContext);
		return ActionResultType.PASS;
	}

	@Override
	public void appendHoverText(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		if (Screen.hasShiftDown()) {
			list.add(new TranslationTextComponent("tooltip.chaosadditions.aiot_1").withStyle(TextFormatting.AQUA));
			list.add(new TranslationTextComponent("tooltip.chaosadditions.aiot_2").withStyle(TextFormatting.AQUA));
			list.add(new TranslationTextComponent("tooltip.chaosadditions.aiot_3").withStyle(TextFormatting.AQUA));
		} else list.add(new TranslationTextComponent("tooltip.chaosadditions.default").withStyle(TextFormatting.AQUA));
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
