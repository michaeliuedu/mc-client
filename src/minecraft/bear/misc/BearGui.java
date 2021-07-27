package bear.misc;

import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopMatrix;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.opengl.GL11;

import bear.Client;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class BearGui{
	
	public static ClientFont mr;
	public Minecraft mc;
	public ScaledResolution resolution;
	public Client client;
	public FontUtils fu;
	
	public BearGui() {
		this.init();
 	}

	public void drawString(String text, float x, float y, int color, boolean centered, double multiple) {
		 
		float width = (float) this.resolution.getScaledWidth();
		float height = (float) this.resolution.getScaledHeight();
		float antiAliasingFactor = resolution.getScaleFactor();
		
		this.resize(x, y, multiple, antiAliasingFactor);

		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		float ratiox = (float) (size.getWidth()/width);
		float ratioy = (float) (size.getHeight()/height);
		float precicex = (float) x / ratiox * antiAliasingFactor;
		float precicey = (float) y  / ratioy * antiAliasingFactor;
		if(centered)this.fu.drawCenteredString(text, precicex , precicey, color);
		else this.fu.drawString(text, precicex, precicey, color);
        GlStateManager.popMatrix();

	}
	
	
	public void drawString(String text, float x, float y, int color) {
		drawString(text, x, y, color, false, 1);
	}	
	
	
	public void drawStringbyDim(String text, float x, float y, int color, boolean centered, boolean mcfont,  double size) {
		
		float width = (float) this.resolution.getScaledWidth();
		float height = (float) 	this.resolution.getScaledHeight();
		float antiAliasingFactor = resolution.getScaleFactor();
		
		this.resize(x, y, size, antiAliasingFactor);
		float newx = x * (int)antiAliasingFactor;
		float newy = y * (int)antiAliasingFactor;
		if(centered && mcfont)	this.fu.drawCenteredString(text, newx, newy, color);
		else if(centered && !mcfont) this.mr.drawCenteredString(text, newx, newy, color);
		else if (!centered && mcfont) this.fu.drawString(text, newx, newy, color);
		else this.mr.drawString(text, newx, newy, color);
		GlStateManager.popMatrix();
	}

	
	public void drawStringbyDim(String text, float x, float y, int color) {		
		drawStringbyDim(text, x, y, color, false, false, 1);
	}

	
	public void drawRect(float x1, float y1, float w, float h, int color) {
		
		
		GL11.glPushMatrix();
		float width = (float) this.resolution.getScaledWidth();
		float height = (float) this.resolution.getScaledHeight();
		float antiAliasingFactor = resolution.getScaleFactor();
		GlStateManager.scale(1/antiAliasingFactor, 1/antiAliasingFactor,1/antiAliasingFactor);
		Gui.drawRect(x1, y1, w, h, color);
		GlStateManager.popMatrix();
        
   }

	
	
	public Pair normalizeDim(float x, float y){
		
		float width = (float) this.resolution.getScaledWidth();
		float height = (float) this.resolution.getScaledHeight();
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		float ratiox = (float) (size.getWidth()/width);
		float ratioy = (float) (size.getHeight()/height);
		
		Pair<Float, Float> dim = new MutablePair<>((x / ratiox * resolution.getScaleFactor()), (y / ratioy * resolution.getScaleFactor()));
		return dim;
	}
	
	
	public Pair normalizeClickDim(float x, float y) {
		float width = (float) this.resolution.getScaledWidth();
		float height = (float) this.resolution.getScaledHeight();
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		float ratiox = (float) (size.getWidth()/width);
		float ratioy = (float) (size.getHeight()/height);
		
		Pair<Float, Float> dim = new MutablePair<>((x / ratiox * (float)(Math.pow((double)(resolution.getScaleFactor()), 2))), (y / ratioy * (float)(Math.pow((double)(resolution.getScaleFactor()), 2))));
		return dim;
	}
	
	
	
	protected void init() {
		
		
		this.fu = new FontUtils("", 0, 30);
		this.mc = Minecraft.getMinecraft();
		this.client = Client.getInstance();
		this.resolution = new ScaledResolution(Minecraft.getMinecraft());
		this.mr = new ClientFont("montserrat", 60);
		this.resolution = new ScaledResolution(Minecraft.getMinecraft());
	}
		
	
	protected void resize(float x, float y, double size, float antiAliasingFactor) {
					
		GL11.glPushMatrix();
		GlStateManager.translate(x, y, 0);
		GlStateManager.scale(size, size, size);
		GlStateManager.translate(-x, -y, 0);
		GlStateManager.scale(1/antiAliasingFactor, 1/antiAliasingFactor,1/antiAliasingFactor);
	}


	
}
