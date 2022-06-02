package me.give_me_moneyz.abhorsen.util;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.vector.Vector3d;
import org.lwjgl.opengl.GL11;

public class LineDrawingUtil {
    public static void drawLine(Vector3d player, Vector3d dest, float width, float r, float g, float b) {
        RenderSystem.lineWidth(width);

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuilder();

        buffer.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_COLOR);
        buffer.vertex(player.x, player.y, player.z).color(r, g, b, 0.5f).endVertex();
        buffer.vertex(dest.x, dest.y, dest.z).color(r, g, b, 0.5f).endVertex();
        tessellator.end();
    }
}
