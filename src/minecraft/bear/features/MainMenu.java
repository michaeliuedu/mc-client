package bear.features;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import org.apache.commons.lang3.math.Fraction;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.*;
import org.lwjgl.opengl.GL11;

import com.ibm.icu.text.SimpleDateFormat;

import java.awt.*;
import bear.*;
import bear.Client;
import bear.misc.FontUtils;
import bear.misc.BearGui;
import bear.misc.ClientFont;
import bear.misc.UnicodeFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Session;

public class MainMenu extends GuiScreen {
		
	public static GuiButton button;
	public static FontUtils comforta;
	public ScaledResolution resolution;
	public Client client;
	public BearGui render;
	
	public MainMenu() {
		
	}
	
	public void initGui() {
		Client.getInstance().getDiscordRP().update("Main Menu", "Idling");
		client = Client.getInstance();
        resolution = new ScaledResolution(Minecraft.getMinecraft());
        render = new BearGui();
	}
	
	public void drawScreen(int mousex, int mousey, float partialTicks) {
		     
		
		mc.getTextureManager().bindTexture(new ResourceLocation("bear/temp_png.png"));
		this.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, this.width, this.height, this.width, this.height);		
		this.drawGradientRect(0, height-100, width, height, 0x00000000, 0xff000000);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		render.drawStringbyDim(client.getInstance().name, width/2, height/2, -1, true, false, 1);
		render.drawStringbyDim(client.getInstance().version, width/100, height/100, -1, false, false, 0.5);
		
		
		Calendar rightNow = Calendar.getInstance();
		int hour = rightNow.get(Calendar.HOUR);
		int minute = rightNow.get(Calendar.MINUTE);
		
		render.drawStringbyDim(String.valueOf(hour) + ":" + (minute > 9 ? minute : "0" + minute), width/2, (float) (height/1.9), -1, true, true, 1);
		render.drawRect((float) (size.getHeight()/2), 100, 1200, 30, -1);
		
		
		String[] buttons = {"Singleplayer", "Multiplayer", "Settings", "Language", "Quit"};

		
		int count = 0;
		for(String name : buttons) {
			float x = (width/buttons.length) * count + (width/buttons.length)/2f + 8 -mc.fontRendererObj.getStringWidth(name)/2f;
			float y = height -20;
			boolean hovered = (mousex >= x && mousey >=y && mousex < x + mc.fontRendererObj.getStringWidth(name) && mousey < y + mc.fontRendererObj.FONT_HEIGHT);
			boolean language = false;
			
			if(name == "Language") language = true;
			
			Gui.drawRect((int)x - 12 , height - mc.fontRendererObj.FONT_HEIGHT + 2, language? (int)x + (name.length() * 5) + 3 : (int)x + (name.length() * 5) - 5, (int)y - 3, 0x90000000);
			this.drawCenteredString(mc.fontRendererObj, name, (int) ((width/buttons.length) * count + (width/buttons.length)/2f), height - 20, hovered ? 0xffDC143C : -1);
			count++;
		}		

		
	}
	
	
	public void mouseClicked(int mousex, int mousey, int button) {
		String[] buttons = {"Singleplayer", "Multiplayer", "Settings", "Language", "Quit"};
		int count = 0;
		System.out.println(render.normalizeClickDim(mousex, mousey));
		
		
		//Calculations for precice mouse click
		for(String name : buttons) {
			float x = (width/buttons.length) * count + (width/buttons.length)/2f + 8 -mc.fontRendererObj.getStringWidth(name)/2f;
			if(name == "Language"){x -= 10;}
			float y = height -20;
			if(mousex >= x && mousey >=y && mousex < x + mc.fontRendererObj.getStringWidth(name) && mousey < y + mc.fontRendererObj.FONT_HEIGHT) {
				switch(name){
					case "Singleplayer":
						mc.displayGuiScreen(new GuiSelectWorld(this));
						break;
						
					case "Multiplayer":
						mc.displayGuiScreen(new GuiMultiplayer(this));
						break;
						
					case "Settings":
						mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
						break;
					case "Language":
						mc.displayGuiScreen(new GuiLanguage(this, mc.gameSettings, mc.getLanguageManager()));
						break;
					case "Quit":
						mc.shutdown();
						break;
				
				}
				
				
			}
			
			count ++;
		}
		
	
	}
	
	public void onGuiClosed() {
		GlStateManager.popMatrix();
		System.out.print("Close");
	}
	
	@Override
	public void updateScreen() {
		// TODO Auto-generated method stub
		super.updateScreen();
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);
	}

}
