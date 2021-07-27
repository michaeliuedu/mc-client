package bear.modules.normal;

import bear.Client;
import bear.event.EventTarget;
import bear.event.impl.RenderEvent;
import bear.gui.normods.mod.Module;

public class TimeChanger extends Module{

	public TimeChanger() {
		super("Time Changer", Category.RENDER);
		
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void toggle() {
		if(!Client.getInstance().modManager.time.enabled) {
			mc.theWorld.setWorldTime(16000);

		}
		super.toggle();
	}
	
}
