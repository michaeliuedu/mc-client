package bear.modules.normal;

import java.lang.reflect.Field;

import bear.Client;
import bear.event.EventTarget;
import bear.event.impl.ClientTickEvent;
import bear.gui.HudConfigScreen;
import bear.gui.config.ClickGUI;
import bear.gui.normods.mod.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;

public class MotionBlur extends Module{

	public MotionBlur() {
		super("Motion Blur", Category.RENDER);
	}
	
	@EventTarget
	public void event(ClientTickEvent e) {
        Minecraft.getMinecraft().addScheduledTask(() -> Minecraft.getMinecraft().entityRenderer.stopUseShader());
		if(Client.getInstance().modManager.motionblur.enabled) {
			if(Minecraft.getMinecraft().currentScreen instanceof GuiScreen && !(Minecraft.getMinecraft().currentScreen instanceof HudConfigScreen)) {
				if(Minecraft.getMinecraft().theWorld != null && !isFastRenderEnabled()) {
					Minecraft.getMinecraft().entityRenderer.loadShader(new ResourceLocation("/shaders/blur.json"));
				}
			}
			else {
				
		        Minecraft.getMinecraft().addScheduledTask(() -> Minecraft.getMinecraft().entityRenderer.stopUseShader());
				Minecraft.getMinecraft().entityRenderer.loadShader(new ResourceLocation("/shaders/phosphor.json"));

			}
		}
		if(Client.getInstance().modManager.motionblur.enabled == false && (mc.currentScreen instanceof ClickGUI) && mc.theWorld != null && isFastRenderEnabled() == false) {
			Minecraft.getMinecraft().entityRenderer.loadShader(new ResourceLocation("/shaders/blur.json"));
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
