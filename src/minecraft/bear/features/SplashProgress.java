package bear.features;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import bear.misc.UnicodeFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;

public class SplashProgress {

	private static final int MAX = 7;
	private static int PROGRESS = 0;
	private static String current = "";
	private static ResourceLocation splash;
	private static UnicodeFontRenderer ufr;

	public static void update() {
		if(Minecraft.getMinecraft() == null || Minecraft.getMinecraft().getLanguageManager() == null) {
			return;
		}
		
		drawSplash(Minecraft.getMinecraft().getTextureManager());
	}
	
	public static void setProgress(int givenProgress, String givenText) {
		PROGRESS = givenProgress;
		current = givenText;
		update();
	}
	
	public static void drawSplash(TextureManager tm) {
		
		ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
		int scaledFactor = scaledResolution.getScaleFactor();
		
		Framebuffer framebuffer = new Framebuffer(scaledResolution.getScaledWidth() * scaledFactor, scaledResolution.getScaledHeight() * scaledFactor, true);
		framebuffer.bindFramebuffer(false);
		
		GlStateManager.matrixMode(GL11.GL_PROJECTION);
		GlStateManager.loadIdentity();
		GlStateManager.ortho(0.0D, (double)scaledResolution.getScaledWidth(), (double)scaledResolution.getScaledHeight(), 0.0D, 1000.0D, 3000.0D);
		GlStateManager.matrixMode(GL11.GL_MODELVIEW);
		GlStateManager.loadIdentity();
		GlStateManager.translate(0.0F, 0.0F, -2000.0F);
		GlStateManager.disableLighting();
		GlStateManager.disableFog();
		GlStateManager.disableDepth();
		GlStateManager.enableTexture2D();
		
		if(splash == null) {
			splash = new ResourceLocation("bear/temp_png.png");
		}
		
		tm.bindTexture(splash);
		
		GlStateManager.resetColor();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		
		Gui.drawScaledCustomSizeModalRect(0, 0, 0, 0, 1920, 1080, scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight(), 1920, 1080);
		drawProgress();
		framebuffer.unbindFramebuffer();
		framebuffer.framebufferRender(scaledResolution.getScaledWidth() * scaledFactor, scaledResolution.getScaledHeight() * scaledFactor);
		
		GlStateManager.enableAlpha();
		GlStateManager.alphaFunc(516, 0.1F);
		
		Minecraft.getMinecraft().updateDisplay();
		
	}
	
	private static void drawProgress() {
		 if(Minecraft.getMinecraft().gameSettings == null || Minecraft.getMinecraft().getTextureManager() == null) {
			 return;
		 }
		 
		 if(ufr == null) ufr = UnicodeFontRenderer.getFontOnPC("Calibri", 15);
		 
			ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
			double nProgress = (double)PROGRESS;
			double calc = (nProgress/MAX) * scaledResolution.getScaledWidth();
			Gui.drawRect(0, scaledResolution.getScaledHeight() - 35, scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight(), 0x114a4a4a);
		
			ufr.drawString(current, 20, scaledResolution.getScaledHeight() - 25, -1);
			
			String step = PROGRESS + "/" + MAX;
			ufr.drawString(step, scaledResolution.getScaledWidth() - 20 - ufr.getStringWidth(step), scaledResolution.getScaledHeight() - 25, -1);
			Gui.drawRect(0, scaledResolution.getScaledHeight() - 2, (int)calc, scaledResolution.getScaledHeight(), -1);
			Gui.drawRect(0, scaledResolution.getScaledHeight() -2 , scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight(), 1);
			
			GlStateManager.resetColor();
			resetTextures();
	}
	
	private static void resetTextures() {
		GlStateManager.textureState[GlStateManager.activeTextureUnit].textureName = -1;
	}
	
}
