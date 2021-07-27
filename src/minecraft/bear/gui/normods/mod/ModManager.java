package bear.gui.normods.mod;

import java.util.ArrayList;

import bear.modules.normal.FullBright;
import bear.modules.normal.MotionBlur;
import bear.modules.normal.OldAnimations;
import bear.modules.normal.TimeChanger;

public class ModManager {

	public ArrayList<Module> mods = new ArrayList<>();
	
	public MotionBlur motionblur;
	public FullBright fullbright;
	public OldAnimations animations;
	public TimeChanger time;
	
	public ModManager() {
		mods.add(motionblur = new MotionBlur());
		mods.add(fullbright = new FullBright());
		mods.add(animations = new OldAnimations());
		mods.add(time = new TimeChanger());
	}
	
}
