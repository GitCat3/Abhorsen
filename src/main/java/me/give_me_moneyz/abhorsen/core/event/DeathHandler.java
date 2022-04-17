package me.give_me_moneyz.abhorsen.core.event;

import me.give_me_moneyz.abhorsen.Abhorsen;
import me.give_me_moneyz.abhorsen.core.init.ItemInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Abhorsen.MOD_ID, bus = Bus.FORGE)
public class DeathHandler {

    @SubscribeEvent
    public static void onDeath(final LivingDeathEvent event){
        if(event.getEntityLiving().getHeldItemMainhand().getItem().toString().equals("ranna")) {
            event.getEntityLiving().setHealth(event.getEntityLiving().getMaxHealth());
            event.setCanceled(true);
            System.out.println("Nope!");
        }
    }
}
