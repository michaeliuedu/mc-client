package bear.modules.normal;

import java.lang.reflect.Field;

import bear.Client;
import bear.event.EventTarget;
import bear.event.impl.ClientTickEvent;
import bear.gui.HudConfigScreen;
import bear.gui.normods.mod.Module;
import bear.misc.ConsoleColors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;

public class FullBright extends Module{

	
	public FullBright() {
		super("Fullbright", Category.RENDER);
	}
	
	@Override
	public void toggle() {
		//Potential error
		super.toggle();
		if(Client.getInstance().modManager.fullbright.enabled) {
			mc.gameSettings.gammaSetting = 100;
		} else if(Client.getInstance().modManager.fullbright.enabled == false) {
			mc.gameSettings.gammaSetting = 1;
		}
	}

	static boolean isFastRenderEnabled() {
        try {
            Field fastRender = GameSettings.class.getDeclaredField("ofFastRender");
            return fastRender.getBoolean(Minecraft.getMinecraft().gameSettings);
        } catch (Exception var1) {
            return false;
        }
	}
	

}
