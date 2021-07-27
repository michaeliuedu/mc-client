package bear.gui.config;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import bear.Client;
import bear.gui.HudConfigScreen;
import bear.gui.config.components.ModButtons;
import bear.gui.normods.NormodButton;
import bear.gui.normods.mod.Module.Category;
import bear.gui.panel.WatermarkPanel;
import bear.misc.ClientFont;
import bear.misc.ConsoleColors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class ModMenu extends GuiScreen{
	
	public boolean hudtoggle, rendertoggle, playertoggle;
	String[] buttons = {"Layout", " Exit"};
	String[] tabs = {"Hud", "Render", "Player"};
	int initw, inith, curw, curh;
	int newx, newy;
	public float antiAliasingFactor;
	ResourceLocation untoggled_arrow = new ResourceLocation("/bear/icons/arrow_left.png");
	ResourceLocation toggled_arrow = new ResourceLocation("/bear/icons/arrow_right.png");
	ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
	public static ClientFont mr;
	public static ClientFont mfr;
	ArrayList<ModButtons> modButtons = new ArrayList<ModButtons>();
	ArrayList<NormodButton> normodButtons = new ArrayList<NormodButton>();
	public ScaledResolution resolution;

	
	@Override
	public void initGui() {
        resolution = new ScaledResolution(Minecraft.getMinecraft());
		if(mr == null) mr = new ClientFont("roboto condensed", 60);
		if(mfr == null) mfr = new ClientFont("roboto condensed", 35);

		super.initGui();
		this.modButtons.clear();
		this.modButtons.add(new ModButtons(Client.getInstance().hudManager.watermark));
		this.modButtons.add(new ModButtons(Client.getInstance().hudManager.fps));
		this.modButtons.add(new ModButtons(Client.getInstance().hudManager.ping));
		this.modButtons.add(new ModButtons(Client.getInstance().hudManager.sprint));
		this.modButtons.add(new ModButtons(Client.getInstance().hudManager.cps));

		this.normodButtons.add(new NormodButton(Client.getInstance().modManager.time));
		this.normodButtons.add(new NormodButton(Client.getInstance().modManager.animations));
		this.normodButtons.add(new NormodButton(Client.getInstance().modManager.motionblur));
		this.normodButtons.add(new NormodButton(Client.getInstance().modManager.fullbright));		
		
		initw = width;
		inith = height;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		antiAliasingFactor = resolution.getScaleFactor();
		curw = width;
		curh = height;
		if(curw != initw || curh != inith) {
			mc.updateDisplay();
		}
		
		GL11.glPushMatrix();
		GlStateManager.scale(1/antiAliasingFactor, 1/antiAliasingFactor,1/antiAliasingFactor);
		
			if(mr == null) mr = new ClientFont("roboto condensed", 60);
			if(mfr == null) mfr = new ClientFont("roboto condensed", 35);

			ScaledResolution sr = new ScaledResolution(mc);
			super.drawScreen(mouseX, mouseY, partialTicks);		
			
			
			if(antiAliasingFactor == 1) {
				newx = width/2;
				newy = height/14;
			} else if(antiAliasingFactor == 2) {
				newx = (int) (width/(antiAliasingFactor - 1));
				newy = (int) (height/(4 * antiAliasingFactor - 1));
			} else if(antiAliasingFactor == 3){
				newx = (int) (width * 3/(antiAliasingFactor - 1));
				newy = (int) (height * 2/(4 * antiAliasingFactor - 3));	
			} else {
				newx = (int) (width * 6/(antiAliasingFactor - 1));
				newy = (int) (height * 3/(4 * antiAliasingFactor - 4));	
				mr.drawCenteredString("Please set your resolution to either small, normal, or large in fullscreen to optimize experience", (int) (width * 6/(antiAliasingFactor - 1)), (height * 2) , -1);
				
			}
			Gui.drawRect(newx/2, newy/2, newx + newx/2, newy * 7, new Color(0, 0, 0, 170).getRGB());		
			Gui.drawRect(newx/2, newy, newx + newx/2, mc.fontRendererObj.FONT_HEIGHT, new Color(0, 0, 0, 250).getRGB());
			mr.drawCenteredString(Client.name, newx, (newy/2) , -1);
			
			GlStateManager.translate((antiAliasingFactor - 1) * width/4, height/10 * antiAliasingFactor, 1);

			int it = 0;
			for(String name : tabs) {
				int offset = 20;
				float x = width/4;
				float y = (it * offset);
				mfr.drawString(name, x + 3, y, -1);
				
				if(hudtoggle) {
					drawImg((int)((x) + 50), (int)0, false, toggled_arrow);
					int os = 20; int iterat = 0;
					for(ModButtons m : modButtons) {
						int xpos = width/4 + 70;
						int ypos = (iterat * os);
						m.draw(xpos, ypos);
						iterat++;
					}		
				}	else {
					drawImg((int)((x) + 50), (int)0, false, untoggled_arrow);
				}
				if(rendertoggle) {
					drawImg((int)((x) + 53), (int)offset, false, toggled_arrow);
					for(NormodButton nm : normodButtons) {				
							int os = 20; int iterat = 0;
							for(NormodButton m : normodButtons) {
								if(nm.mod.cat == Category.RENDER) {
								int xpos = width/4 + 70;
								int ypos = (iterat * os);
								m.draw(xpos, ypos);
								iterat++;
							}		
						}
					}
				}else {
					drawImg((int)((x) + 53), (int)offset, false, untoggled_arrow);
				}
				
				if(playertoggle) {
					drawImg((int)((x) + 50), (int)offset * 2, false, toggled_arrow);

				}else {
					drawImg((int)((x) + 50), (int)offset * 2, false, untoggled_arrow);

				}
				it++;
			}
			
	
		GlStateManager.popMatrix();   

		
		int count = 0;
		for(String name : buttons) {
			float x = (width/buttons.length) * count + (width/buttons.length)/2f + 8 -mc.fontRendererObj.getStringWidth(name)/2f;
			float y = height -20;
			boolean hovered = (mouseX >= x && mouseY >=y && mouseX < x + mc.fontRendererObj.getStringWidth(name) && mouseY < y + mc.fontRendererObj.FONT_HEIGHT);
			Gui.drawRect((int)x - 11 , height - mc.fontRendererObj.FONT_HEIGHT + 2, (int)x + (name.length() * 5) -2, (int)y - 3, 0x90000000);
			this.drawCenteredString(mc.fontRendererObj, name, (int) ((width/buttons.length) * count + (width/buttons.length)/2f), height - 20, hovered ? 0xffDC143C : -1);
			count++;
		}		
		
	
		
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		
		
		if(hudtoggle) {
			int it = 0; antiAliasingFactor = resolution.getScaleFactor();
			for(ModButtons mod : modButtons) {
				int offset = 20;
				float x = ((width/4 + 75) + (antiAliasingFactor - 1) * width/4)/antiAliasingFactor;
				float settingx = ((width/4 + 70 - 19) + (antiAliasingFactor - 1) * width/4)/antiAliasingFactor;
				float y = ((it * offset) + height/10 * antiAliasingFactor)/antiAliasingFactor;
				if(mouseX >= x && mouseY >=y && mouseX < (x + 15 - antiAliasingFactor)&& mouseY < (y + mfr.FONT_HEIGHT)) {
					mod.mod.toggle();
				}

				if(mouseX >= settingx && mouseY >=y && mouseX < (settingx + 15 -  antiAliasingFactor)&& mouseY < (y + mfr.FONT_HEIGHT)) {
					System.out.println("Setting");
					switch(mod.mod.name){
		            case "Watermark":
		                Minecraft.getMinecraft().displayGuiScreen(new WatermarkPanel());
		                break;
		            
		        
		            }
				}
				
				it++;
			}
		}

		if(rendertoggle) {
			int it = 0; antiAliasingFactor = resolution.getScaleFactor();
			for(NormodButton mod : normodButtons) {
				if(mod.mod.cat == Category.RENDER) {
					int offset = 20;
					float x = ((width/4 + 75) + (antiAliasingFactor - 1) * width/4)/antiAliasingFactor;
					float settingx = ((width/4 + 70 - 19) + (antiAliasingFactor - 1) * width/4)/antiAliasingFactor;
					float y = ((it * offset) + height/10 * antiAliasingFactor)/antiAliasingFactor;
					if(mouseX >= x && mouseY >=y && mouseX < (x + 15 - antiAliasingFactor)&& mouseY < (y + mfr.FONT_HEIGHT)) {
						mod.mod.toggle();
					}

					if(mouseX >= settingx && mouseY >=y && mouseX < (settingx + 15 -  antiAliasingFactor)&& mouseY < (y + mfr.FONT_HEIGHT)) {
						System.out.println("Setting");
						switch(mod.mod.name){
			            case "Watermark":
			                Minecraft.getMinecraft().displayGuiScreen(new WatermarkPanel());
			                break;
			            
			        
			            }
					}
					
					it++;
				}			
			}
		}
		
		
		int it = 0; antiAliasingFactor = resolution.getScaleFactor();
		for(String name : tabs) {
			int offset = 20;
			float x = (width/4 + (antiAliasingFactor - 1) * width/4)/antiAliasingFactor;
			float y = ((it * offset) + height/10 * antiAliasingFactor)/antiAliasingFactor;
			System.out.println("X: " + x + " | " + mouseX + " Y: " + y + height/10 * antiAliasingFactor + " | " + mouseY);
			if(mouseX >= x && mouseY >=y && mouseX < (x + mc.fontRendererObj.getStringWidth(name))&& mouseY < (y + mfr.FONT_HEIGHT)) {
				switch(name){
				case "Hud":
					System.out.println("Hud Mods");
					hudtoggle = !hudtoggle;
					rendertoggle = false;
					playertoggle = false;
					break;		
				case "Render":
					System.out.println("Render Mods");
					rendertoggle = !rendertoggle;
					hudtoggle = false;
					playertoggle = false;
					break;
				case "Player":
					playertoggle = !playertoggle;
					rendertoggle = false;
					hudtoggle = false;
					System.out.println("Player Mods");
					break;
												
				}		
			}

			it++;
		}
		System.out.println(" ");		
		
		int count = 0;
		
		for(String name : buttons) {
			float x = (width/buttons.length) * count + (width/buttons.length)/2f + 8 -mc.fontRendererObj.getStringWidth(name)/2f;
			float y = height -20;
			if(mouseX >= x && mouseY >=y && mouseX < x + mc.fontRendererObj.getStringWidth(name) && mouseY < y + mc.fontRendererObj.FONT_HEIGHT) {
				switch(name){
					case "Layout":
						Minecraft.getMinecraft().displayGuiScreen(new HudConfigScreen());
						break;
						
					case " Exit":
						Minecraft.getMinecraft().displayGuiScreen(null);
						break;
				}				
				
			}
			
			count ++;
		}
		
		super.mouseClicked(mouseX, mouseY, mouseButton);

	}
	
	private void drawImg(int x, int y, boolean b, ResourceLocation icon) {
    	Minecraft.getMinecraft().getTextureManager().bindTexture(icon);
    	Gui.drawModalRectWithCustomSizedTexture(x - 16, b ? y+16:y, 0.0F, 0.0F, 16, 16, 16, 16);
	}
	
	
	
}
