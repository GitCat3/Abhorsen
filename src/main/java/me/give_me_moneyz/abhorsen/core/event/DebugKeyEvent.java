package me.give_me_moneyz.abhorsen.core.event;

import me.give_me_moneyz.abhorsen.Abhorsen;
import me.give_me_moneyz.abhorsen.core.init.PacketHandler;
import me.give_me_moneyz.abhorsen.core.network.DebugKeyPacket;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Abhorsen.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DebugKeyEvent {
    @SubscribeEvent
    public static void DebugKeyPress(final InputEvent.KeyInputEvent event) {
        if(Abhorsen.TEST.isDown()) {
            PacketHandler.INSTANCE.sendToServer(new DebugKeyPacket());
        }
    }
}
