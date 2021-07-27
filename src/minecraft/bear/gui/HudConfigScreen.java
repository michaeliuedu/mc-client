package bear.gui;

import bear.Client;
import bear.gui.mod.HudMod;
import net.minecraft.client.gui.GuiScreen;

public class HudConfigScreen extends GuiScreen{

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		for(HudMod m : Client.getInstance().hudManager.hudMods) {
			if(m.isEnabled()) {
				m.renderDummy(mouseX, mouseY);
			}
		}
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		
	}
	
}
 