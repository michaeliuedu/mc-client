package bear.modules.hud;

import java.awt.Color;

import bear.Client;
import bear.gui.mod.HudMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class FramesCounter extends HudMod{
	
	public boolean box = false;
	public boolean subtext = false;
	public FramesCounter() {
		super("Frames", 5, 15);
	}
	
	@Override
	public void draw() {			
		Minecraft.getMinecraft().fontRendererObj.drawString(Integer.toString(Minecraft.getMinecraft().getDebugFPS()), getX(), getY(), -1);

		super.draw();
		
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {
		Minecraft.getMinecraft().fontRendererObj.drawString(Integer.toString(Minecraft.getMinecraft().getDebugFPS()), getX(), getY(), -1);
		super.renderDummy(mouseX, mouseY);

	}

	@Override
	public int getHeight() {
		return 3;
	}
}
