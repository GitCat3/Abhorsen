package me.give_me_moneyz.abhorsen;

import me.give_me_moneyz.abhorsen.core.init.BlockInit;
import me.give_me_moneyz.abhorsen.core.init.ItemInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("abhorsen")
public class Abhorsen {

    public static final String MOD_ID = "abhorsen";
    public static final ItemGroup ABHORSENGROUP = new AbhorsenGroup("abhorsen");

    public Abhorsen() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        ItemInit.ITEMS.register(bus);
        BlockInit.BLOCKS.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {

    }

    public static class AbhorsenGroup extends ItemGroup {
        public AbhorsenGroup(String label) {
            super(label);
        }

        @Override
        public ItemStack createIcon() {
            return ItemInit.RANNA.get().getDefaultInstance();
        }
    }
}
