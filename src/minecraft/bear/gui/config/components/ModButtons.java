package bear.gui.config.components;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import bear.gui.mod.HudMod;
import bear.gui.normods.mod.Module;
import bear.gui.panel.WatermarkPanel;
import bear.misc.ClientFont;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class ModButtons {

	public HudMod mod;
	public static ClientFont mfr;	
	ResourceLocation toggled_arrow = new ResourceLocation("/bear/icons/untoggle.png");
	ResourceLocation untoggled_arrow = new ResourceLocation("/bear/icons/toggle.png");
	ResourceLocation settings_icon = new ResourceLocation("/bear/icons/settings.png");
	public ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
	int antiAliasingFactor, width, height;
	public ModButtons(HudMod m){
		width = sr.getScaledWidth();
		height = sr.getScaledHeight();
		antiAliasingFactor = sr.getScaleFactor();
		this.mod = m;
		
	}
	

	public void draw(int x, int y) {
        if(mfr == null) mfr = new ClientFont("roboto condensed", 20);
        drawImg(x + 19, y, false, getButton());
        drawImg(x, y, false, settings_icon);
        mfr.drawString(mod.name, x+ 25, y+2, -1);
		
		

	}
	
	private int getColor() {
		if(mod.isEnabled()) {
			return new Color(0, 255, 0, 255).getRGB();
		} else {
			return new Color(255, 0, 0, 255).getRGB();
		} 
	}
	
	private ResourceLocation getButton() {
		if(mod.isEnabled()) {
			return toggled_arrow;
		}else {
			return untoggled_arrow;
		}
	}
	
	public void onClick(int mouseX, int mouseY, int button) {
		

	}
	
	private void drawImg(int x, int y, boolean b, ResourceLocation icon) {
    	Minecraft.getMinecraft().getTextureManager().bindTexture(icon);
    	Gui.drawModalRectWithCustomSizedTexture(x - 16, b ? y+16:y, 0.0F, 0.0F, 16, 16, 16, 16);
	}
	
	
}
