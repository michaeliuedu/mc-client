package bear.modules.hud;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import bear.Client;
import bear.gui.mod.HudMod;
import net.minecraft.client.Minecraft;

public class CPSCounter extends HudMod{

	private List<Long> clicks = new ArrayList<Long>();
	private boolean wasPressed;
	private long lastPressed;
	
	public CPSCounter() {
		super("Click Speed", 5, 50);
	}
	
	@Override
	public void draw() {			
		
		final boolean pressed = Mouse.isButtonDown(0);
		if(pressed != this.wasPressed) {
			this.lastPressed = System.currentTimeMillis();
			this.wasPressed = pressed;
			if(pressed) {
				this.clicks.add(this.lastPressed);
			}
		}
		
		Minecraft.getMinecraft().fontRendererObj.drawString(Integer.toString(getCPS()), getX(), getY(), -1);
		super.draw();
		
	}
	
	private int getCPS() {
		final long time = System.currentTimeMillis();
		this.clicks.removeIf(aLong -> aLong + 1000 < time);
		return this.clicks.size();
	}

	@Override
	public void renderDummy(int mouseX, int mouseY) {
		Minecraft.getMinecraft().fontRendererObj.drawString(Integer.toString(getCPS()), getX(), getY(), -1);
		super.renderDummy(mouseX, mouseY);

	}
	
	

}
