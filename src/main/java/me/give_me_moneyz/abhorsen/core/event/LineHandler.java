package me.give_me_moneyz.abhorsen.core.event;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import me.give_me_moneyz.abhorsen.Abhorsen;
import me.give_me_moneyz.abhorsen.core.capability.AbhorsenPlayerCapability;
import me.give_me_moneyz.abhorsen.core.enums.PlayerPossibleCapabilities;
import me.give_me_moneyz.abhorsen.core.network.DrawLinePacket;
import me.give_me_moneyz.abhorsen.util.LineDrawingUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.util.NonNullConsumer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nonnull;
import java.util.List;


@Mod.EventBusSubscriber(modid = Abhorsen.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LineHandler {
    @SubscribeEvent
    public static void DrawLine(final RenderWorldLastEvent event) {
        PlayerEntity player = Minecraft.getInstance().player;
        List<Vector3d> entitypos = DrawLinePacket.entitypos;
        if (player == null || !player.isAlive() || Minecraft.getInstance().options.getCameraType().isMirrored()) {
            return;
        }


        MatrixStack stack = event.getMatrixStack();
        stack.pushPose();
        Vector3d view = Minecraft.getInstance().cameraEntity.getEyePosition(event.getPartialTicks());
        stack.translate(-view.x, -view.y, -view.z);

        // TODO investigate depreciation

        RenderSystem.pushMatrix();
        RenderSystem.multMatrix(stack.last().pose());
        RenderSystem.disableTexture();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.polygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        RenderSystem.enableBlend();

        double dist = 1;
        double yaw = ((Minecraft.getInstance().player.yRot + 90) * Math.PI) / 180;
        double pitch = ((Minecraft.getInstance().player.xRot + 90) * Math.PI) / 180;

        Vector3d playervec = view.add(MathHelper.sin((float) pitch) * MathHelper.cos((float) yaw) * dist, MathHelper.cos((float) pitch) * dist - 0.35, MathHelper.sin((float) pitch) * MathHelper.sin((float) yaw) * dist);

        player.getCapability(AbhorsenPlayerCapability.CAPABILITY).ifPresent(abhorsenPlayerCapability -> {
            if(abhorsenPlayerCapability.getAbilities() != PlayerPossibleCapabilities.NONE) {
                if(player.isShiftKeyDown()) {
                    if(!entitypos.isEmpty()) {
                        for(Vector3d position : entitypos) {
                            LineDrawingUtil.drawLine(playervec, position, 1.5F, 1F, 0F, 0F);
                        }
                    }
                }
            }
        });

        RenderSystem.polygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
        RenderSystem.disableBlend();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(true);
        RenderSystem.enableTexture();
        RenderSystem.popMatrix();
        stack.popPose();
    }
}
