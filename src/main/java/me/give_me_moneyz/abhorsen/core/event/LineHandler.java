package me.give_me_moneyz.abhorsen.core.event;

import com.mojang.blaze3d.systems.RenderSystem;
import me.give_me_moneyz.abhorsen.Abhorsen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.opengl.GL11;


@Mod.EventBusSubscriber(modid = Abhorsen.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LineHandler {
    @SubscribeEvent
    public static void DrawLine(final RenderWorldLastEvent event) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuilder();
        Entity entity = Minecraft.getInstance().getCameraEntity();
        assert entity != null;
        RenderSystem.lineWidth(1.5f);

        buffer.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_COLOR);
        buffer.vertex((float) entity.getX(), (float) entity.getY(), (float) entity.getZ()).color(0f, 0.6f, 1f, 0.5f).endVertex();
        buffer.vertex(0, 0, 0).color(0f, 0.6f, 1f, 0.5f).endVertex();
        tessellator.end();
    }
}
