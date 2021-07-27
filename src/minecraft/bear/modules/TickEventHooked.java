package bear.modules;

import java.lang.reflect.Field;

import bear.Client;
import bear.event.EventTarget;
import bear.event.impl.ClientTickEvent;
import bear.gui.HudConfigScreen;
import bear.gui.config.ClickGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;

public class TickEventHooked {

	Minecraft mc = Minecraft.getMinecraft();

	@EventTarget
	public void event(ClientTickEvent e) {
		//System.out.println(ConsoleColors.RED + "[Bear Client] EVENT: Client tick event" + ConsoleColors.RESET);
		if(Minecraft.getMinecraft().gameSettings.MOD_MENU.isPressed()) {
			Minecraft.getMinecraft().displayGuiScreen(new ClickGUI());
		}
		
		
		
		
	}
	
}
