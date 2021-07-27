package bear.features;

import net.arikia.dev.drpc.*;
import net.arikia.dev.drpc.callbacks.ReadyCallback;

public class DiscordRP {

	
	private boolean running = true;
	private long created = 0;
	
	public void start() {
		

		this.created = System.currentTimeMillis();
		DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(new ReadyCallback(){
			
			@Override
			public void apply(DiscordUser user) {
				System.out.println("Found local Discord account: " + user.username + "#" + user.discriminator);
				update("Booting", "Idling");
		
				
				
			}
		}).build();
	
		DiscordRPC.discordInitialize("849694264640536597", handlers, true);
		new Thread("Discord RPC Callback") {
			
			@Override
			public void run() {
				while(running) {
					DiscordRPC.discordRunCallbacks();
				}
			}
		}.start();
		
	}
	
	public void shutdown() {
		running = false;
		DiscordRPC.discordShutdown();
	}
	
	public void update(String firstLine, String secondLine) {
		DiscordRichPresence.Builder b = new DiscordRichPresence.Builder(secondLine);
		b.setBigImage("large", "");
		b.setDetails(firstLine);
		b.setStartTimestamps(created);
		DiscordRPC.discordUpdatePresence(b.build());
	}
}
