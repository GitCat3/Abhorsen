package me.give_me_moneyz.abhorsen.core.network;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class DrawLinePacket {
    private final double xPos;
    private final double yPos;
    private final double zPos;

    public DrawLinePacket(double x, double y, double z) {
        this.xPos = x;
        this.yPos = y;
        this.zPos = z;
    }

    public static DrawLinePacket decode(PacketBuffer packetBuffer) {
        return new DrawLinePacket(packetBuffer.readDouble(), packetBuffer.readDouble(), packetBuffer.readDouble());
    }

    public void encode(PacketBuffer packetBuffer) {
        packetBuffer.writeDouble(this.xPos);
        packetBuffer.writeDouble(this.yPos);
        packetBuffer.writeDouble(this.zPos);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            System.out.println(this.xPos);
            System.out.println(this.yPos);
            System.
        });
        ctx.get().setPacketHandled(true);
    }
}
