package com.blackout.chaosadditions;

import com.blackout.chaosadditions.config.CADCommonConfig;
import com.blackout.chaosadditions.config.CADServerConfig;
import com.blackout.chaosadditions.data.*;
import com.blackout.chaosadditions.events.BiomeLoadEventSubscriber;
import com.blackout.chaosadditions.events.LoginEventHandler;
import com.blackout.chaosadditions.registry.CADBlocks;
import com.blackout.chaosadditions.registry.CADGlobalLootModifier;
import com.blackout.chaosadditions.registry.CADItems;
import io.github.chaosawakens.ChaosAwakens;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DatagenModLoader;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forgespi.language.IModInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.maven.artifact.versioning.ArtifactVersion;
import software.bernie.geckolib3.GeckoLib;

import java.util.Optional;

@Mod(ChaosAdditions.MODID)
public class ChaosAdditions {
	public static final String MODID = "chaosadditions";
	public static final String MODNAME = "Chaos Additions";
	public static final Logger LOGGER = LogManager.getLogger();
	public static ArtifactVersion VERSION = null;

	public ChaosAdditions() {
		GeckoLib.initialize();

		Optional<? extends ModContainer> opt = ModList.get().getModContainerById(MODID);
		if (opt.isPresent()) {
			IModInfo modInfo = opt.get().getModInfo();
			VERSION = modInfo.getVersion();
		} else LOGGER.warn("Cannot get version from mod info");

		LOGGER.debug(MODNAME + " is an Addon for: " + ChaosAwakens.MODNAME + "!");
		LOGGER.debug(MODNAME + " Version is: " + VERSION);
		LOGGER.debug("Mod ID for " + MODNAME + " is: " + MODID);
		LOGGER.debug("Mod ID for " + ChaosAwakens.MODNAME + " is: " + ChaosAwakens.MODID);

		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

		eventBus.addListener(this::gatherData);

		CADBlocks.ITEM_BLOCKS.register(eventBus);
		CADBlocks.BLOCKS.register(eventBus);
		CADItems.ITEMS.register(eventBus);
		if (ModList.get().isLoaded("purechaos") || DatagenModLoader.isRunningDataGen()) CADItems.ITEMS_PURECHAOS.register(eventBus);
		CADGlobalLootModifier.LOOT_MODIFIER_SERIALIZERS.register(eventBus);

		MinecraftForge.EVENT_BUS.register(new LoginEventHandler());
		MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, BiomeLoadEventSubscriber::onBiomeLoadingEvent);
		MinecraftForge.EVENT_BUS.register(this);

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CADCommonConfig.COMMON_SPEC);
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, CADServerConfig.SERVER_SPEC);
	}

	private void gatherData(final GatherDataEvent event) {
		DataGenerator dataGenerator = event.getGenerator();
		final ExistingFileHelper existing = event.getExistingFileHelper();
		if (event.includeClient()) {
			dataGenerator.addProvider(new CADItemModelGenerator(dataGenerator, existing));
		}
		if (event.includeServer()) {
			dataGenerator.addProvider(new CADRecipeGenerator(dataGenerator));
			dataGenerator.addProvider(new CADLootTableProvider(dataGenerator));
			dataGenerator.addProvider(new CADLootProvider(dataGenerator));
			dataGenerator.addProvider(new CADTagProvider.CADItemTagProvider(dataGenerator, existing));
		}
	}
}
