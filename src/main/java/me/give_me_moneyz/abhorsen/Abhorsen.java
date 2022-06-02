package me.give_me_moneyz.abhorsen;

import me.give_me_moneyz.abhorsen.core.capability.AbhorsenPlayerCapability;
import me.give_me_moneyz.abhorsen.core.init.BlockInit;
import me.give_me_moneyz.abhorsen.core.init.ItemInit;
import me.give_me_moneyz.abhorsen.core.init.PacketHandler;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.lwjgl.glfw.GLFW;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("abhorsen")
public class Abhorsen {

    public static final String MOD_ID = "abhorsen";
    public static final ItemGroup ABHORSENGROUP = new AbhorsenGroup("abhorsen");
    public static final KeyBinding TEST = new KeyBinding("key.test", KeyConflictContext.UNIVERSAL, InputMappings.Type.KEYSYM, GLFW.GLFW_KEY_J, "key.categories.test");

    public Abhorsen() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        bus.addListener(this::clientSetup);
        ItemInit.ITEMS.register(bus);
        BlockInit.BLOCKS.register(bus);
        PacketHandler.init();
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        AbhorsenPlayerCapability.register();
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ClientRegistry.registerKeyBinding(TEST);
    }

    public static class AbhorsenGroup extends ItemGroup {
        public AbhorsenGroup(String label) {
            super(label);
        }

        @Override
        public ItemStack makeIcon() {
            return ItemInit.RANNA.get().getDefaultInstance();
        }
    }
}
