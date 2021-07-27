package bear.gui.mod;

import java.util.ArrayList;

import bear.modules.hud.CPSCounter;
import bear.modules.hud.FramesCounter;
import bear.modules.hud.PingCounter;
import bear.modules.hud.ToggleSprint;
import bear.modules.hud.Watermark;

public class HudManager {
	
	public ArrayList<HudMod> hudMods = new ArrayList<>();
	
	public Watermark watermark;
	public FramesCounter fps;
	public PingCounter ping;
	public ToggleSprint sprint;
	public CPSCounter cps;
	
	public HudManager() {
		hudMods.add(watermark = new Watermark());
		hudMods.add(fps = new FramesCounter());
		hudMods.add(ping = new PingCounter());
		hudMods.add(sprint = new ToggleSprint());
		hudMods.add(cps = new CPSCounter());
	}
	
	public void renderMods() {
		for(HudMod m: hudMods) {
			if(m.isEnabled()) {
				m.draw();
			}
		}
	}
}
