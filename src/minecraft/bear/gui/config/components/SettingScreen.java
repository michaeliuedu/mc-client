package bear.gui.config.components;

import java.awt.Color;

import bear.gui.mod.HudMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class SettingScreen {
	public int x, y, w, h;
	public HudMod mod;
	
	public SettingScreen(int x, int y, int w, int h, HudMod m){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.mod = m;

	}
	
	public void draw() {
		Gui.drawRect(x, y, x + w, y + h, new Color(0, 0, 0, 170).getRGB());
		Minecraft.getMinecraft().fontRendererObj.drawString(mod.name, x+2, y+2, 1);
	}
	
	public String getName(HudMod m) {
		return m.name;
	}
	
	
	
}
