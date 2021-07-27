package bear.gui.config.components;

import java.awt.Color;

import bear.Client;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;

public class RGBGui {
	
	public Minecraft mc = Minecraft.getMinecraft();
	public ScaledResolution sr = new ScaledResolution(mc);
	public static GuiTextField r;
	public static GuiTextField g;
	public static GuiTextField b;
	public static GuiTextField alpha;
	

	public int x, y, w, h;
	
	public RGBGui(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public void init(int red, int green, int blu, int a) {
		r = new GuiTextField(-2001, Minecraft.getMinecraft().fontRendererObj, x + 7, y, w, h);
		r.setMaxStringLength(3);
		r.setText(Integer.toString(red));
		g = new GuiTextField(-2002, Minecraft.getMinecraft().fontRendererObj, x + w + 18, y, w, h);
		g.setMaxStringLength(3);
		g.setText(Integer.toString(green));
		b = new GuiTextField(-2003, Minecraft.getMinecraft().fontRendererObj, x + w*2 + 30, y, w, h);
		b.setMaxStringLength(3);
		b.setText(Integer.toString(blu));
		alpha = new GuiTextField(-2004, Minecraft.getMinecraft().fontRendererObj, x + w*3 + 40, y, w, h);
		alpha.setMaxStringLength(3);
		alpha.setText(Integer.toString(a));
	
	
	
	}
	
	
	public void draw() {
		r.drawTextBox();
		g.drawTextBox();
		b.drawTextBox();
		alpha.drawTextBox();
		mc.fontRendererObj.drawStringWithShadow("R", x, y, 0xffAA0000);
		mc.fontRendererObj.drawStringWithShadow("G", x + 10 + w, y, 0xff55FF55);
		mc.fontRendererObj.drawStringWithShadow("B", x + 22 + w*2, y, 0xff5555FF);
		mc.fontRendererObj.drawString("A", x + w * 3 + 33, y, -1);
	}
	
	public void click(int x, int y, int k) {
		r.mouseClicked(x, y, k);
		g.mouseClicked(x, y, k);
		b.mouseClicked(x, y, k);
		alpha.mouseClicked(x, y, k);

	}
	
	public void update() {
		r.updateCursorCounter();
		g.updateCursorCounter();
		b.updateCursorCounter();
		alpha.updateCursorCounter();

	}
	
	public void typed(char type, int code) {
		r.textboxKeyTyped(type, code);
		g.textboxKeyTyped(type, code);
		b.textboxKeyTyped(type, code);
		alpha.textboxKeyTyped(type, code);

	}
	
	public void renderPreview(int x, int y, int w, int h) {
		Gui.drawRect(x - 1, y - 1, x + w + 1, y + h + 1, -1);
		Gui.drawRect(x, y, x + w, y + h, getColor().getRGB());

	}
	
	public Color getColor() {
		String rc, gc, bc, ac;
		rc = r.getText();
		gc = g.getText();
		bc = b.getText();
		ac = alpha.getText();
		int valalph, valred, valblue, valgreen;
	
		
		
		//Below is depreciated code for rounding rgb values to percentages. Removed due to errors in converting back in other classes 
		//(Error in reversing calculations and rounding)
//		try {
//			valalph = Math.round(Integer.parseInt(ac) * 2.55F);
//			valred = Math.round(Integer.parseInt(rc) * 2.55F);
//			valgreen = Math.round(Integer.parseInt(gc) * 2.55F);
//			valblue = Math.round(Integer.parseInt(bc) * 2.55F);
//			
//			if(valalph > 255){
//				alpha.setText("100");
//				valalph = 255;
//			}else if(valred > 255) {
//				r.setText("100");
//				valred = 255;
//				
//			}else if(valblue > 255) {
//				b.setText("100");
//				valblue = 255;
//			}else if(valgreen > 255) {
//				g.setText("100");
//				valgreen = 255;
//			}
//
//		}catch(Exception e) {
//			valalph = 255;
//			valred = 255;
//			valgreen = 255;
//			valblue = 255;
//		}
//		
		try {
			valalph =Integer.parseInt(ac);
			valred = Integer.parseInt(rc);
			valgreen = Integer.parseInt(gc);
			valblue = Integer.parseInt(bc);		
			return new Color(valred, valgreen, valblue, valalph);

		}catch (Exception e){
			return new Color(255, 255, 255, 255);
		}
	}
}
