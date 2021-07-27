package bear.modules.hud;

import bear.gui.mod.HudMod;
import net.minecraft.client.Minecraft;

public class PingCounter extends HudMod{

	public int ping;
	public PingCounter() {
	super("Ping", 5, 30);
}

@Override
public void draw() {		
	if(Minecraft.getMinecraft().isSingleplayer() == false) {
		ping = Minecraft.getMinecraft().getNetHandler().getPlayerInfo(Minecraft.getMinecraft().thePlayer.getUniqueID()).getResponseTime();
	}else {
		ping = -1;
	}
	
	Minecraft.getMinecraft().fontRendererObj.drawString(Integer.toString(ping), getX(), getY(), -1);
	super.draw();

}

@Override
public void renderDummy(int mouseX, int mouseY) {
	Minecraft.getMinecraft().fontRendererObj.drawString(Integer.toString(ping), getX(), getY(), -1);
	super.renderDummy(mouseX, mouseY);

}

@Override
public int getHeight() {
	// TODO Auto-generated method stub
	return 3;
}

@Override
public int getWidth() {
	// TODO Auto-generated method stub
	return 5;
}
}
