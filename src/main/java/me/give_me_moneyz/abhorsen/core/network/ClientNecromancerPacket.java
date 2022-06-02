package me.give_me_moneyz.abhorsen.core.network;

import me.give_me_moneyz.abhorsen.core.capability.AbhorsenPlayerCapability;
import me.give_me_moneyz.abhorsen.core.enums.PlayerPossibleCapabilities;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import java.util.function.Supplier;

public class ClientNecromancerPacket {

    private final PlayerPossibleCapabilities abilities;

    public ClientNecromancerPacket(PlayerPossibleCapabilities ability) {
        this.abilities = ability;
    }

    public static ClientNecromancerPacket decode(PacketBuffer packetBuffer) {
        return new ClientNecromancerPacket(packetBuffer.readEnum(PlayerPossibleCapabilities.class));
    }

    public void encode(PacketBuffer packetBuffer) {
        packetBuffer.writeEnum(this.abilities);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            PlayerEntity player = Minecraft.getInstance().player;
            player.getCapability(AbhorsenPlayerCapability.CAPABILITY).ifPresent(abhorsenPlayerCapability -> {
                abhorsenPlayerCapability.setAbilities(this.abilities);
            });
            System.out.println("handled client side Necromancy");
        });
        ctx.get().setPacketHandled(true);
    }
}