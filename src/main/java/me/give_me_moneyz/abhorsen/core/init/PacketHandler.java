package me.give_me_moneyz.abhorsen.core.init;

import me.give_me_moneyz.abhorsen.Abhorsen;
import me.give_me_moneyz.abhorsen.core.network.DebugKeyPacket;
import me.give_me_moneyz.abhorsen.core.network.DrawLinePacket;
import me.give_me_moneyz.abhorsen.core.network.ClientNecromancerPacket;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public final class PacketHandler {
    private static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(Abhorsen.MOD_ID, "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    private PacketHandler() {

    }

    public static void init() {
        int index = 0;
        INSTANCE.registerMessage(++index, DrawLinePacket.class, DrawLinePacket::encode, DrawLinePacket::decode, DrawLinePacket::handle);
        INSTANCE.registerMessage(++index, DebugKeyPacket.class, DebugKeyPacket::encode, DebugKeyPacket::decode, DebugKeyPacket::handle);
        INSTANCE.registerMessage(++index, ClientNecromancerPacket.class, ClientNecromancerPacket::encode, ClientNecromancerPacket::decode, ClientNecromancerPacket::handle);
    }

}
