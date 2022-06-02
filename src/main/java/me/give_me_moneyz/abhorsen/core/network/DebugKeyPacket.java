package me.give_me_moneyz.abhorsen.core.network;

import me.give_me_moneyz.abhorsen.core.capability.AbhorsenPlayerCapability;
import me.give_me_moneyz.abhorsen.core.enums.PlayerPossibleCapabilities;
import me.give_me_moneyz.abhorsen.core.init.PacketHandler;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.function.Supplier;

public class DebugKeyPacket {
    public DebugKeyPacket() {
    }

    public static DebugKeyPacket decode(PacketBuffer packetBuffer) {
        return new DebugKeyPacket();
    }

    public static void encode(DebugKeyPacket debugkeypacket, PacketBuffer packetBuffer) {
    }

    public static void handle(DebugKeyPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity player = ctx.get().getSender();
            player.getCapability(AbhorsenPlayerCapability.CAPABILITY).ifPresent(abhorsenPlayerCapability -> {
                if(abhorsenPlayerCapability.getAbilities() == PlayerPossibleCapabilities.ABHORSEN) {
                    abhorsenPlayerCapability.setAbilities(PlayerPossibleCapabilities.NONE);
                    player.sendMessage(ITextComponent.nullToEmpty("[Debug] You are no longer Abhorson"), Util.NIL_UUID);
                }else if(abhorsenPlayerCapability.getAbilities() == PlayerPossibleCapabilities.NONE) {
                    abhorsenPlayerCapability.setAbilities(PlayerPossibleCapabilities.ABHORSEN);
                    player.sendMessage(ITextComponent.nullToEmpty("[Debug] You are now Abhorson"), Util.NIL_UUID);
                }
                PacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new ClientNecromancerPacket(abhorsenPlayerCapability.getAbilities()));
            });
        });

        ctx.get().setPacketHandled(true);
    }
}
