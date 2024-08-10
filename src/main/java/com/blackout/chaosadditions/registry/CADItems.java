package com.blackout.chaosadditions.registry;

import com.blackout.chaosadditions.ChaosAdditions;
import com.blackout.chaosadditions.config.CADServerConfig;
import com.blackout.chaosadditions.items.*;
import com.blackout.chaosadditions.util.CADEnumUtils;
import io.github.chaosawakens.common.registry.CAItemGroups;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = ChaosAdditions.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CADItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ChaosAdditions.MODID);
	public static final DeferredRegister<Item> ITEMS_PURECHAOS = DeferredRegister.create(ForgeRegistries.ITEMS, ChaosAdditions.MODID);

	public static final RegistryObject<AIOTItem> WOODEN_AIOT = ITEMS.register("wooden_aiot", () -> new AIOTItem(CADEnumUtils.CADItemTier.WOOD, () -> CADServerConfig.SERVER.woodenAIOTDamage, -2.4F, new Item.Properties().tab(CAItemGroups.EQUIPMENT)));
	public static final RegistryObject<AIOTItem> STONE_AIOT = ITEMS.register("stone_aiot", () -> new AIOTItem(CADEnumUtils.CADItemTier.STONE, () -> CADServerConfig.SERVER.stoneAIOTDamage, -2.4F, new Item.Properties().tab(CAItemGroups.EQUIPMENT)));
	public static final RegistryObject<AIOTItem> GOLDEN_AIOT = ITEMS.register("golden_aiot", () -> new AIOTItem(CADEnumUtils.CADItemTier.GOLD, () -> CADServerConfig.SERVER.goldenAIOTDamage, -2.4F, new Item.Properties().tab(CAItemGroups.EQUIPMENT)));
	public static final RegistryObject<AIOTItem> IRON_AIOT = ITEMS.register("iron_aiot", () -> new AIOTItem(CADEnumUtils.CADItemTier.IRON, () -> CADServerConfig.SERVER.ironAIOTDamage, -2.4F, new Item.Properties().tab(CAItemGroups.EQUIPMENT)));
	public static final RegistryObject<AIOTItem> DIAMOND_AIOT = ITEMS.register("diamond_aiot", () -> new AIOTItem(CADEnumUtils.CADItemTier.DIAMOND, () -> CADServerConfig.SERVER.diamondAIOTDamage, -2.4F, new Item.Properties().tab(CAItemGroups.EQUIPMENT)));
	public static final RegistryObject<AIOTItem> NETHERITE_AIOT = ITEMS.register("netherite_aiot", () -> new AIOTItem(CADEnumUtils.CADItemTier.NETHERITE, () -> CADServerConfig.SERVER.netheriteAIOTDamage, -2.4F, new Item.Properties().tab(CAItemGroups.EQUIPMENT)));

	public static final RegistryObject<AIOTItem> CRYSTALWOOD_AIOT = ITEMS.register("crystalwood_aiot", () -> new AIOTItem(CADEnumUtils.CADItemTier.TOOL_CRYSTALWOOD, () -> CADServerConfig.SERVER.crystalWoodAIOTDamage, -2.4F, new Item.Properties().tab(CAItemGroups.EQUIPMENT)));
	public static final RegistryObject<AIOTItem> KYANITE_AIOT = ITEMS.register("kyanite_aiot", () -> new AIOTItem(CADEnumUtils.CADItemTier.TOOL_KYANITE, () -> CADServerConfig.SERVER.kyaniteAIOTDamage, -2.4F, new Item.Properties().tab(CAItemGroups.EQUIPMENT)));
	public static final RegistryObject<AIOTItem> PINK_TOURMALINE_AIOT = ITEMS.register("pink_tourmaline_aiot", () -> new AIOTItem(CADEnumUtils.CADItemTier.TOOL_PINK_TOURMALINE, () -> CADServerConfig.SERVER.pinkTourmalineAIOTDamage, -2.4F, new Item.Properties().tab(CAItemGroups.EQUIPMENT)));
	public static final RegistryObject<AIOTItem> CATS_EYE_AIOT = ITEMS.register("cats_eye_aiot", () -> new AIOTItem(CADEnumUtils.CADItemTier.TOOL_CATS_EYE, () -> CADServerConfig.SERVER.catsEyeAIOTDamage, -2.4F, new Item.Properties().tab(CAItemGroups.EQUIPMENT)));
	public static final RegistryObject<AIOTItem> COPPER_AIOT = ITEMS.register("copper_aiot", () -> new AIOTItem(CADEnumUtils.CADItemTier.TOOL_COPPER, () -> CADServerConfig.SERVER.copperAIOTDamage, -2.4F, new Item.Properties().tab(CAItemGroups.EQUIPMENT)));
	public static final RegistryObject<AIOTItem> TIN_AIOT = ITEMS.register("tin_aiot", () -> new AIOTItem(CADEnumUtils.CADItemTier.TOOL_TIN, () -> CADServerConfig.SERVER.tinAIOTDamage, -2.4F, new Item.Properties().tab(CAItemGroups.EQUIPMENT)));
	public static final RegistryObject<AIOTItem> SILVER_AIOT = ITEMS.register("silver_aiot", () -> new AIOTItem(CADEnumUtils.CADItemTier.TOOL_SILVER, () -> CADServerConfig.SERVER.silverAIOTDamage, -2.4F, new Item.Properties().tab(CAItemGroups.EQUIPMENT)));
	public static final RegistryObject<AIOTItem> PLATINUM_AIOT = ITEMS.register("platinum_aiot", () -> new AIOTItem(CADEnumUtils.CADItemTier.TOOL_PLATINUM, () -> CADServerConfig.SERVER.platinumAIOTDamage, -2.4F, new Item.Properties().tab(CAItemGroups.EQUIPMENT)));
	public static final RegistryObject<AIOTItem> EMERALD_AIOT = ITEMS.register("emerald_aiot", () -> new EnchantedAIOTItem(CADEnumUtils.CADItemTier.TOOL_EMERALD, () -> CADServerConfig.SERVER.emeraldAIOTDamage, -2.4F, new Item.Properties().tab(CAItemGroups.EQUIPMENT),
			() -> new EnchantmentData[]{new EnchantmentData(Enchantments.SILK_TOUCH, 1)}));
	public static final RegistryObject<AIOTItem> AMETHYST_AIOT = ITEMS.register("amethyst_aiot", () -> new AIOTItem(CADEnumUtils.CADItemTier.TOOL_AMETHYST, () -> CADServerConfig.SERVER.amethystAIOTDamage, -2.4F, new Item.Properties().tab(CAItemGroups.EQUIPMENT)));
	public static final RegistryObject<AIOTItem> TIGERS_EYE_AIOT = ITEMS.register("tigers_eye_aiot", () -> new AIOTItem(CADEnumUtils.CADItemTier.TOOL_TIGERS_EYE, () -> CADServerConfig.SERVER.tigersEyeAIOTDamage, -2.4F, new Item.Properties().tab(CAItemGroups.EQUIPMENT)));
	public static final RegistryObject<AIOTItem> RUBY_AIOT = ITEMS.register("ruby_aiot", () -> new AIOTItem(CADEnumUtils.CADItemTier.TOOL_RUBY, () -> CADServerConfig.SERVER.rubyAIOTDamage, -2.4F, new Item.Properties().tab(CAItemGroups.EQUIPMENT)));
	public static final RegistryObject<AIOTItem> ULTIMATE_AIOT = ITEMS.register("ultimate_aiot", () -> new EnchantedAIOTItem(CADEnumUtils.CADItemTier.TOOL_ULTIMATE, () -> CADServerConfig.SERVER.ultimateAIOTDamage, -2.4F, new Item.Properties().tab(CAItemGroups.EQUIPMENT),
			() -> new EnchantmentData[]{new EnchantmentData(Enchantments.BLOCK_EFFICIENCY, 5), new EnchantmentData(Enchantments.BLOCK_FORTUNE, 3), new EnchantmentData(Enchantments.UNBREAKING, 3)}));

	public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire", () -> new Item(new Item.Properties().tab(CAItemGroups.ITEMS)));

	public static final RegistryObject<SwordItem> SAPPHIRE_SWORD = ITEMS.register("sapphire_sword", () -> new SapphireSwordItem(CADEnumUtils.CADItemTier.TOOL_SAPPHIRE, () -> CADServerConfig.SERVER.sapphireSwordDamage, -2.4F, new Item.Properties().tab(CAItemGroups.EQUIPMENT)));
	public static final RegistryObject<PickaxeItem> SAPPHIRE_PICKAXE = ITEMS.register("sapphire_pickaxe", () -> new SapphirePickaxeItem(CADEnumUtils.CADItemTier.TOOL_SAPPHIRE, () -> CADServerConfig.SERVER.sapphirePickaxeDamage, -2.8F, new Item.Properties().tab(CAItemGroups.EQUIPMENT)));
	public static final RegistryObject<AxeItem> SAPPHIRE_AXE = ITEMS.register("sapphire_axe", () -> new SapphireAxeItem(CADEnumUtils.CADItemTier.TOOL_SAPPHIRE, () -> CADServerConfig.SERVER.sapphireAxeDamage, -3F, new Item.Properties().tab(CAItemGroups.EQUIPMENT)));
	public static final RegistryObject<ShovelItem> SAPPHIRE_SHOVEL = ITEMS.register("sapphire_shovel", () -> new SapphireShovelItem(CADEnumUtils.CADItemTier.TOOL_SAPPHIRE, () -> CADServerConfig.SERVER.sapphireShovelDamage, -3F, new Item.Properties().tab(CAItemGroups.EQUIPMENT)));
	public static final RegistryObject<HoeItem> SAPPHIRE_HOE = ITEMS.register("sapphire_hoe", () -> new SapphireHoeItem(CADEnumUtils.CADItemTier.TOOL_SAPPHIRE, () -> CADServerConfig.SERVER.sapphireHoeDamage, 0F, new Item.Properties().tab(CAItemGroups.EQUIPMENT)));
	public static final RegistryObject<AIOTItem> SAPPHIRE_AIOT = ITEMS.register("sapphire_aiot", () -> new SapphireAIOTItem(CADEnumUtils.CADItemTier.TOOL_SAPPHIRE, () -> CADServerConfig.SERVER.sapphireAIOTDamage, -2.4F, new Item.Properties().tab(CAItemGroups.EQUIPMENT)));

	public static final RegistryObject<ArmorItem> SAPPHIRE_HELMET = ITEMS.register("sapphire_helmet", () -> new SapphireArmorItem(CADEnumUtils.CADArmorMaterial.SAPPHIRE, EquipmentSlotType.HEAD, new Item.Properties().tab(CAItemGroups.EQUIPMENT)));
	public static final RegistryObject<ArmorItem> SAPPHIRE_CHESTPLATE = ITEMS.register("sapphire_chestplate", () -> new SapphireArmorItem(CADEnumUtils.CADArmorMaterial.SAPPHIRE, EquipmentSlotType.CHEST, new Item.Properties().tab(CAItemGroups.EQUIPMENT)));
	public static final RegistryObject<ArmorItem> SAPPHIRE_LEGGINGS = ITEMS.register("sapphire_leggings", () -> new SapphireArmorItem(CADEnumUtils.CADArmorMaterial.SAPPHIRE, EquipmentSlotType.LEGS, new Item.Properties().tab(CAItemGroups.EQUIPMENT)));
	public static final RegistryObject<ArmorItem> SAPPHIRE_BOOTS = ITEMS.register("sapphire_boots", () -> new SapphireArmorItem(CADEnumUtils.CADArmorMaterial.SAPPHIRE, EquipmentSlotType.FEET, new Item.Properties().tab(CAItemGroups.EQUIPMENT)));

	// Pure Chaos Support
	public static final RegistryObject<AIOTItem> MEGANIUM_AIOT = ITEMS_PURECHAOS.register("meganium_aiot", () -> new EnchantedAIOTItem(CADEnumUtils.CADItemTier.TOOL_MEGANIUM, () -> CADServerConfig.SERVER.meganiumAIOTDamage, -2.4F, new Item.Properties().tab(CAItemGroups.EQUIPMENT),
						() -> new EnchantmentData[]{new EnchantmentData(Enchantments.BLOCK_EFFICIENCY, 5), new EnchantmentData(Enchantments.BLOCK_FORTUNE, 3), new EnchantmentData(Enchantments.UNBREAKING, 3)}));
}
