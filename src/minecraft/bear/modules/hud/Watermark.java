package bear.modules.hud;

import java.awt.Color;

import bear.Client;
import bear.gui.mod.HudMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class Watermark extends HudMod{

	public String watermark = "Bear Client";
	public boolean box = false;
	public Color color = new Color(255, 255, 255);
	
	public Watermark() {
		super("Watermark", 5, 5);
	}
	
	@Override
	public void draw() {	
		
		if(box) {
			Gui.drawRect(getX() - 3, getY() - 3, getX() + watermark.length() * 6, getY() + mc.fontRendererObj.FONT_HEIGHT, 0x991c1c1c);
		}
		Minecraft.getMinecraft().fontRendererObj.drawString(watermark, getX(), getY(), color.getRGB());
		super.draw();
		
		
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {
		Minecraft.getMinecraft().fontRendererObj.drawString(watermark, getX(), getY(), color.getRGB());
		super.renderDummy(mouseX, mouseY);

	}
	
	


}
