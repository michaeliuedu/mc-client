package bear.features;

import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.util.ResourceLocation;

public class ServerDataFeatured extends ServerData{

	public static final ResourceLocation ICON = new ResourceLocation("bear/featured_icon.png");
	
	//Needs constructor because superclass ServerData requires the information
	public ServerDataFeatured(String servername, String serverIP) {
		super(servername, serverIP, false);
		
	}

}
