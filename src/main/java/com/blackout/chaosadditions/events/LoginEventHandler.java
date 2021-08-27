package com.blackout.chaosadditions.events;

import com.blackout.chaosadditions.ChaosAdditions;
import com.blackout.chaosadditions.UpdateHandler;
import com.blackout.chaosadditions.config.ChaosAdditionsConfig;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class LoginEventHandler {
    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        PlayerEntity player = event.getPlayer();
        if (UpdateHandler.show && ChaosAdditionsConfig.COMMON.showUpdateMessage.get())
            player.sendMessage(new TranslationTextComponent(UpdateHandler.updateStatus), player.getUUID());
        ChaosAdditions.LOGGER.debug(UpdateHandler.updateStatus);
        ChaosAdditions.LOGGER.debug(UpdateHandler.show);
    }

    @OnlyIn(Dist.CLIENT)
    public void sendChatMessage(String text, PlayerEntity player) {
        TranslationTextComponent component2 = new TranslationTextComponent(I18n.get(text, new Object[0]));
        player.sendMessage(component2, player.getUUID());
    }
}