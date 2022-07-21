package kr.sagye.ui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;

public class RenderingHelper {

    private static Minecraft mc = Minecraft.getMinecraft();
    private static final HashMap<String, ResourceLocation> MAP = new HashMap<String, ResourceLocation>();
    public static float zDepth = 0.0F;

    public static void drawTexture(ResourceLocation texture, float x, float y, float width, float height, float alpha) {
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        GlStateManager.loadIdentity(); //여러 개 불러올 때 초기화

        GlStateManager.translate(x, y, -2000); // 텍스쳐 위치지정
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR); // int 값의 파라미터, 텍스쳐 맵핑*, 화소 설정, 질감 설정 등등
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR); // int 값의 파라미터, 텍스쳐 맵핑*, 화소 설정, 질감 설정 등등

        /* 여기까진 기본 세팅( 초기화 / 텍스쳐 맵핑 )*/

        Minecraft.getMinecraft().renderEngine.bindTexture(texture);//텍스쳐 불러옴

        GlStateManager.pushMatrix();
        GlStateManager.enableTexture2D();

        /*투명 값 입력을 위한 설정*/
        GlStateManager.enableBlend();
        GlStateManager.depthMask(false);
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        /*텍스쳐 인스턴스 불러와서 여러 설정해주는부분*/
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buf = tessellator.getBuffer();

        buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);

        GlStateManager.color(1.0F, 1.0F, 1.0F, alpha); //Alpha값은 1.0이 100% 0.1이 10%

/*
        buf.pos(x + width, y, zDepth).tex(1.0F, 0.0F).endVertex();
        buf.pos(x, y, zDepth).tex(0.0F, 0.0F).endVertex();
        buf.pos(x, y + height, zDepth).tex(0.0F, 1.0F).endVertex();
        buf.pos(x + width, y + height, zDepth).tex(1.0F, 1.0F).endVertex();
*/

        buf.pos(x, y + height, zDepth).tex(0.0F, 1.0F).endVertex();
        buf.pos(x + width, y + height, zDepth).tex(1.0F, 1.0F).endVertex();
        buf.pos(x + width, y, zDepth).tex(1.0F, 0.0F).endVertex();
        buf.pos(x, y, zDepth).tex(0.0F, 0.0F).endVertex();

        tessellator.draw();

        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
        GlStateManager.popMatrix();

    }

    public static void drawText(String text, int x, int y, float scale, int color, float alpha) {

        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.scale(scale, scale, scale);
        int width = mc.fontRenderer.getStringWidth(text);
        int height = mc.fontRenderer.FONT_HEIGHT;
        float textalpha = 25.0F;

        if ((alpha * 255.0F) + 25.0F >= 255.0F) {
            textalpha = 255.0F;
        } else if ((alpha * 255.0F) + 25.0F <= 25.0F) {
            textalpha = 25.0F;
        } else if ((alpha * 255.0F) + 25.0F <= 255.0F && (alpha * 255.0F) + 25.0F >= 25.0F) {
            textalpha = (alpha * 255.0F) + 25.0F;
        }

        mc.fontRenderer.drawStringWithShadow(text, x - (width / 2), y - (height / 2), color | ((int) textalpha << 24) - color);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static void drawSkinhead(int x, int y, float scale) {
        GlStateManager.pushMatrix();
        ResourceLocation skinhead = ((AbstractClientPlayer) mc.player).getLocationSkin();
        mc.getTextureManager().bindTexture(skinhead);
        GlStateManager.color(1,1,1,1);
        GlStateManager.scale(scale,scale,scale);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 24, 24, 24, 24, 192, 192);
        GlStateManager.popMatrix();
    }

}
