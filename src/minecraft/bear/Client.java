package bear;

import bear.event.EventManager;
import bear.features.DiscordRP;
import bear.features.SplashProgress;
import bear.gui.mod.HudManager;
import bear.gui.normods.mod.ModManager;
import bear.misc.ConsoleColors;
import bear.modules.TickEventHooked;
import bear.modules.hud.ToggleSprint;
import bear.modules.normal.MotionBlur;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class Client {
	
	public static String name = "Bear Client";
	public static String version = "v0.0.1";
	public HudManager hudManager;
	public ModManager modManager;
	public FontRenderer font;
	public Minecraft mc = Minecraft.getMinecraft();
	public static final Client INSTANCE = new Client();
	public static final Client getInstance() {
		return INSTANCE;
	}
	
	private DiscordRP discordRP = new DiscordRP();

	
	public void init() {

		System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "[Bear Client] [Client runtime/INFO]: launching " + name + " stable release version " + version + ConsoleColors.RESET);
		SplashProgress.setProgress(1, "Launching native client");
        
		EventManager.register(new TickEventHooked());
		EventManager.register(new ToggleSprint());
		EventManager.register(new MotionBlur());
		modManager = new ModManager();
		hudManager = new HudManager();
		discordRP.start();
		
	}
	


	public void shutdown() {
		System.out.println(ConsoleColors.RED_BOLD_BRIGHT+ "[Bear Client] [Client runtime/INFO]: shutdown event on instance of " + name + " stable release version " + version + ". We hope to see you again!" + ConsoleColors.RESET);
		discordRP.shutdown();
		
	}
	
	public DiscordRP getDiscordRP() {
		return discordRP;
	}


	
}
