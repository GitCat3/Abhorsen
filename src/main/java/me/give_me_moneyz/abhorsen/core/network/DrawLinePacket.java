package me.give_me_moneyz.abhorsen.core.network;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class DrawLinePacket {
    private final double xPos;
    private final double yPos;
    private final double zPos;
    public static List<Vector3d> entitypos = new ArrayList<>();

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
            System.out.println(this.zPos);
            if(!entitypos.contains(new Vector3d(this.xPos, this.yPos, this.zPos))) {
                entitypos.add(new Vector3d(this.xPos, this.yPos, this.zPos));
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
