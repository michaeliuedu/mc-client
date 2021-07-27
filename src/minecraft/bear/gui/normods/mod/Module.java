package bear.gui.normods.mod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class Module {

	public Minecraft mc = Minecraft.getMinecraft();
	public FontRenderer fr = mc.fontRendererObj;
	public String name;
	public boolean enabled;
	public Category cat;
	public int x, y;
	
	public Module(String name, Category c) {
		this.name = name;
		this.cat = c;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public void toggle() {
		this.setEnabled(!enabled);
	}
	
	 public boolean isEnabled() {
		 return enabled;
	 }
	 
	 public enum Category{
		MOVEMENT("Movement"),
		PLAYER("Player"),
		RENDER("Render");
			
		public String name;
			Category(String name){
				this.name = name;
			}
		}	
}
