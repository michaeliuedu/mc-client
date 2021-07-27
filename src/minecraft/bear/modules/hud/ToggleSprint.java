package bear.modules.hud;

import bear.Client;
import bear.event.EventTarget;
import bear.event.impl.ClientTickEvent;
import bear.gui.HudConfigScreen;
import bear.gui.mod.HudMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class ToggleSprint extends HudMod{

	public boolean sprinting;
	public ToggleSprint() {
		super("Toggle Sprint", 5, 45);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void draw() {			
		Minecraft.getMinecraft().fontRendererObj.drawString("Sprinting" + (Client.getInstance().hudManager.sprint.sprinting ? " [Enabled]" : " [Disabled]"), getX(), getY(), -1);
		super.draw();
		
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {
		Minecraft.getMinecraft().fontRendererObj.drawString("Sprinting" + (Client.getInstance().hudManager.sprint.sprinting ? " [Enabled]" : " [Disabled]"), getX(), getY(), -1);
		super.renderDummy(mouseX, mouseY);

	}
	
	
	
	@EventTarget
	public void event(ClientTickEvent e) {

		if(Minecraft.getMinecraft().gameSettings.keyBindSprint.isPressed()) {
			Client.getInstance().hudManager.sprint.sprinting = !Client.getInstance().hudManager.sprint.sprinting;
		}
	
		
		if(mc.thePlayer != null && Client.getInstance().hudManager.sprint.sprinting && Client.getInstance().hudManager.sprint.enabled && mc.thePlayer.moveForward > 0 && mc.thePlayer.isCollidedHorizontally == false && mc.thePlayer.isSneaking() == false && mc.thePlayer.isUsingItem() == false) {
			mc.thePlayer.setSprinting(true);
		}
		
	}
}
