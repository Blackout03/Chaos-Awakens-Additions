package com.blackout.chaosadditions.events;

import com.blackout.chaosadditions.ChaosAdditions;
import io.github.chaosawakens.manager.CAConfigManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.VersionChecker;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class LoginEventHandler {
	private static final String DOWNLOADS = "https://www.curseforge.com/minecraft/mc-mods/chaos-additions/files";

	@SubscribeEvent
	public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
		Entity entity = event.getEntity();

		if (CAConfigManager.MAIN_COMMON.showUpdateMessage.get() && VersionChecker.getResult(ModList.get().getModContainerById(ChaosAdditions.MODID).get().getModInfo()).status == VersionChecker.Status.OUTDATED) {
			entity.sendMessage(new StringTextComponent("A new version of ").withStyle(TextFormatting.WHITE)
					.append(new StringTextComponent(ChaosAdditions.MODNAME).withStyle(TextFormatting.BOLD, TextFormatting.GOLD))
					.append(new StringTextComponent(" is now available from: ").withStyle(TextFormatting.WHITE))
					.append(new StringTextComponent(DOWNLOADS)
							.withStyle((style) -> style
									.withColor(TextFormatting.GOLD)
									.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, DOWNLOADS)))), Util.NIL_UUID);
		}
	}
}
