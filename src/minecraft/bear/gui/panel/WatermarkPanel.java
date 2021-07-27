package bear.gui.panel;

import java.awt.Color;
import java.io.IOException;

import bear.Client;
import bear.gui.config.components.RGBGui;
import bear.modules.hud.Watermark;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;

public class WatermarkPanel extends GuiScreen{

	
	public static RGBGui rgb;
	public static GuiTextField textField;
	private int x, y, width, height;
	public Color textcolor;
	
	public Watermark w = Client.getInstance().hudManager.watermark;
	
	@Override
	public void initGui() {
		// TODO Auto-generated method stub
		super.initGui();
		rgb = new RGBGui(210, 145, 30, 10);
		textcolor = Client.getInstance().hudManager.watermark.color;
		textField = new GuiTextField(1, Minecraft.getMinecraft().fontRendererObj, 210, mc.fontRendererObj.FONT_HEIGHT + 65, 100, 15);
		textField.setMaxStringLength(34);
		textField.setText(Client.getInstance().hudManager.watermark.watermark);
		rgb.init(w.color.getRed(), w.color.getGreen(), w.color.getBlue(), w.color.getAlpha());
		x = 212; y = 112; width = height = 10;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		// TODO Auto-generated method stub
		super.drawScreen(mouseX, mouseY, partialTicks);
		ScaledResolution sr = new ScaledResolution(mc);
		super.drawScreen(mouseX, mouseY, partialTicks);
		Gui.drawRect(200, 50, sr.getScaledWidth() - 200, sr.getScaledHeight() -50, new Color(0, 0, 0, 170).getRGB());
		Gui.drawRect(200, 50, sr.getScaledWidth() - 200, mc.fontRendererObj.FONT_HEIGHT * 4, new Color(0, 0, 0, 170).getRGB());
		mc.fontRendererObj.drawStringWithShadow("Watermark Module ®", 210, 42, new Color(250, 0, 0, 0).getRGB());
		mc.fontRendererObj.drawStringWithShadow("Custom Watermark:", 210, 62, -1);
		mc.fontRendererObj.drawStringWithShadow("Toggle Outline:", 210, 102, -1);
		mc.fontRendererObj.drawStringWithShadow("Text Color:", 210, 132, -1);

		Gui.drawRect(x, y, x + width, y + height, getColor());
		Client.getInstance().hudManager.watermark.color = rgb.getColor();
		rgb.draw();
		textField.drawTextBox();
		rgb.renderPreview(213, 164, 160, 30);

	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		// TODO Auto-generated method stub
		textField.mouseClicked(mouseX, mouseY, mouseButton);
		super.mouseClicked(mouseX, mouseY, mouseButton);
		rgb.click(mouseX, mouseY, mouseButton);
		
		if(mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height) {
			Client.getInstance().hudManager.watermark.box = !Client.getInstance().hudManager.watermark.box;
		}
	}
	
	@Override
	public void updateScreen() {
		// TODO Auto-generated method stub
		textField.updateCursorCounter();
		rgb.update();
		Client.getInstance().hudManager.watermark.watermark = textField.getText();
		super.updateScreen();
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		// TODO Auto-generated method stub
		rgb.typed(typedChar, keyCode);
		textField.textboxKeyTyped(typedChar, keyCode);
		super.keyTyped(typedChar, keyCode);
	}
	
	private int getColor() {
		if(Client.getInstance().hudManager.watermark.box == true) {
			return new Color(0, 255, 0, 255).getRGB();
		} else {
			return new Color(255, 0, 0, 255).getRGB();
		}
	}
}
