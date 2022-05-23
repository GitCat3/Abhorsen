package me.give_me_moneyz.abhorsen.core.event;

import me.give_me_moneyz.abhorsen.Abhorsen;
import me.give_me_moneyz.abhorsen.core.init.PacketHandler;
import me.give_me_moneyz.abhorsen.core.network.DrawLinePacket;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = Abhorsen.MOD_ID, bus = Bus.FORGE)
public class DeathHandler {

    @SubscribeEvent
    public static void onDeath(final LivingDeathEvent event){
        double x = event.getEntity().getX();
        double y = event.getEntity().getY();
        double z = event.getEntity().getZ();
        PacketHandler.INSTANCE.send(PacketDistributor.ALL.noArg(), new DrawLinePacket(x, y, z));
    }
}
